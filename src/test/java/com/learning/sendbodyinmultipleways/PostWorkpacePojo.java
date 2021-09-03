package com.learning.sendbodyinmultipleways;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pojo.workspace.Workspace;
import com.pojo.workspace.WorkspaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class PostWorkpacePojo {
	
	
	@BeforeClass
	public void settingUp() {	
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setBaseUri("https://api.getpostman.com");
		reqSpecBuilder.setContentType(ContentType.JSON);
		reqSpecBuilder.addHeader("x-api-key", "PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		reqSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = reqSpecBuilder.build();
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
		RestAssured.responseSpecification = responseSpecBuilder.build();
	}
	
	@Test(dataProvider = "WorkspaceType")
	public void createWorkspaceViaPojo(String name, String type, String description) {
		
		Workspace wp = new Workspace(name, type, description);
		WorkspaceRoot wr = new WorkspaceRoot(wp);
		
		WorkspaceRoot deserialized = given().
		body(wr).
		when().
		post("/workspaces").
		then().
		log().
		all().
		extract().
		response().
		as(WorkspaceRoot.class);
		
		assertThat(deserialized.getWorkspace().getName(),equalTo(wr.getWorkspace().getName()));
		assertThat(deserialized.getWorkspace().getId(),matchesPattern("^[a-z0-9-]{36}$"));

	}
	
	@DataProvider(name = "WorkspaceType")
	public Object[][] getWorkspaces() {
		return new Object[][] {
				{"MehulPOJO1","personal","Created Via Pojo Class"}
		};
	}
	
}