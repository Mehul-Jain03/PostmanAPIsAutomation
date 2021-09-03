package com.learning.workspaceapis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class GetWorkplaceQueryParam {
	
	@BeforeClass
	public void settingUp() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.addHeader("x-api-key", "PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
		RestAssured.responseSpecification = responseSpecBuilder.build();
		
	}
	
	@Test
	public void getWorkspacesAsStringVariable() {
		given().
		queryParam("mehul", "jain").
		queryParam("karan", "thakur").
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).
		body("workspaces.name",contains("Team Workspace","My Workspace","New Personal Workspace","New Workspace"),
				"workspaces.name",containsInAnyOrder("My Workspace","Team Workspace","New Personal Workspace","New Workspace"),
				"workspaces.name",is(not(emptyArray())),
				"workspaces.name",hasSize(4),
				"workspaces[0]",hasKey("id"),
				"workspaces[0]",hasValue("Team Workspace"),
				"workspaces[0]",hasEntry("type", "team"));
		
	}
	
}
