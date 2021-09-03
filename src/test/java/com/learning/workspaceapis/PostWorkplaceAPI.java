package com.learning.workspaceapis;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PostWorkplaceAPI {
	
	
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
	public void createWorkSpaceBDD() {
		
		//This is with BDD
		
		String payload = "{\n"
				+ " \"workspace\": {\n"
				+ "        \"name\": \"Automation\",\n"
				+ "        \"type\": \"personal\",\n"
				+ "        \"description\": \"Created Via RestAssured Automation\"\n"
				+ "        }\n"
				+ "}";
		
		given().
		body(payload).
		when().post("/workspaces").then().
		log().
		all().
		assertThat().
		body("workspace.name", is(equalTo("Automation")),
				"workspace.id",matchesPattern("^[a-z0-9-]{36}$"));
		
	}
	
	@Test
	public void createWorkSpaceNonBDD() {
		
		//This is Non BDD
		
		String payload = "{\n"
				+ " \"workspace\": {\n"
				+ "        \"name\": \"Automation\",\n"
				+ "        \"type\": \"personal\",\n"
				+ "        \"description\": \"Created Via RestAssured Automation\"\n"
				+ "        }\n"
				+ "}";
		
		Response res = with().
		body(payload).
		post("/workspaces");
		
		assertThat(res.path("workspace.name"),equalTo("Automation"));
		assertThat(res.path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
		
	}


}
