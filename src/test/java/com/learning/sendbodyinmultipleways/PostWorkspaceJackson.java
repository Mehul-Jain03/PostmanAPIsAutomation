package com.learning.sendbodyinmultipleways;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class PostWorkspaceJackson {
	
	// Nested Body Via JacksonAPI
	
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
	public void createWorkSpaceBDD() throws JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper();
		
		ObjectNode nestedObjectNode = om.createObjectNode();
		
		nestedObjectNode.put("name","Automation Nested Map");
		nestedObjectNode.put("type", "personal");
		nestedObjectNode.put("description", "Created Via RestAssured Automation");
		
		ObjectNode mainObjectNode = om.createObjectNode();
		
		mainObjectNode.set("workspace", nestedObjectNode);
		
		String mainObjString =  om.writeValueAsString(mainObjectNode);
		
		
		given().
		body(mainObjString).
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
