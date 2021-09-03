package com.assignments.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.assignments.serialization.Address;
import com.assignments.serialization.GeoLocation;
import com.assignments.serialization.UserData;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class CreateUserTest {
	
	@BeforeClass
	public void settingUp() {	
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com");
		reqSpecBuilder.setContentType(ContentType.JSON);
		reqSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = reqSpecBuilder.build();
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(201).expectContentType(ContentType.JSON);
		RestAssured.responseSpecification = responseSpecBuilder.build();
	}
	
	@Test
	public void createUserViaPojo() {
		
		GeoLocation geo = new GeoLocation("-37.3159","81.1496");
		Address address = new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geo);
		UserData ud = new UserData("Leanne Graham", "Bret", "Sincere@april.biz", address);
		
		given().
		body(ud).
		when().
		post("/users").
		then().
		log().
		all();

	}
}