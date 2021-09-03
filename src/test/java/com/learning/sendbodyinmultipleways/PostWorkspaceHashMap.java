package com.learning.sendbodyinmultipleways;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class PostWorkspaceHashMap {
	
	// Nested Body Preparation
	
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
		
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, String> nestedMap = new HashMap<String,String>();
		nestedMap.put("name","Automation Nested Map");
		nestedMap.put("type", "personal");
		nestedMap.put("description", "Created Via RestAssured Automation");
		map.put("workspace",nestedMap);
		
		given().
		body(map).
		when().
		post("/workspaces").
		then().
		log().		
		all().
		assertThat().
		body("workspace.name", is(equalTo("Automation Nested Map")),
				"workspace.id",matchesPattern("^[a-z0-9-]{36}$"));
	
		
	}

}