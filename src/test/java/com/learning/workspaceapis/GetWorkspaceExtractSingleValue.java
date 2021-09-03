package com.learning.workspaceapis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

//Explained the ways to get the single value from json object

public class GetWorkspaceExtractSingleValue {
	
	@Test
	public void getWorkspacesAsResponseVariable() {
		Response res =  given().
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).extract().response();
		
		//2nd Way
		JsonPath jsonPath = new JsonPath(res.asString());
		System.out.println("workspace name : " + jsonPath.getString("workspaces[0].name"));
		
		//1st Way
		//System.out.println("workspace name : " + res.path("workspaces[0].name"));
		
	}
	
	
	@Test
	public void getWorkspacesAsStringVariable() {
		String stringRes =  given().
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).extract().response().asString();
		
		//3rd Way
		System.out.println("workspace name : " + JsonPath.from(stringRes).getString("workspaces[0].name"));
		
	}
	
	@Test
	public void getWorkspacesAsStringVariableDirectInPath() {
		String name =  given().
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).extract().response().path("workspaces[0].name");
		
		//4th Way
		//System.out.println("workspace name : " +stringRes);
		
		assertThat(name,equalTo("Team Workspace"));
	}

}