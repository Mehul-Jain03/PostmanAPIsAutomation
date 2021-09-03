package com.learning.postmanecho;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostmanEchoJsonSchemaValidator {
	
	// This Class Validates the Json Schema
	
	@Test
	public void validateJsonSchema() {
		
		given().
		baseUri("https://postman-echo.com").
		log().
		all().
		when().
		get("/get").
		then().
		log().
		all().
		assertThat().
		statusCode(200).
		body(matchesJsonSchemaInClasspath("JsonSchemaValidatorFile.json"));
	}

}