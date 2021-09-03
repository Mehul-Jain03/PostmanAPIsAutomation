package com.learning.workspaceapis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class GetWorkplaceRequestSpecification {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;

	@BeforeClass
	public void initiateRequestSpec() {

//		reqSpec = given().baseUri("https://api.getpostman.com")
//				.header("x-api-key", "PMAK-610024661e3525003b97f46c-200ac43dc79bd8d330ce3957dd1fffae18").log().all();

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("x-api-key", "PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		requestSpecBuilder.log(LogDetail.ALL);
		reqSpec = requestSpecBuilder.build();
		
		/*
		 * resSpec =
		 * RestAssured.expect().statusCode(200).contentType(ContentType.JSON).log().all(
		 * );
		 */
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		resSpec = responseSpecBuilder.build();
	}

	@Test
	public void getWorkspacesValidateStatusCode() {

		// Response response = reqSpec.get("/workspaces").then().log().all().extract().response();
		
		/*
		 * Response response =
		 * given(reqSpec).get("/workspaces").then().log().all().extract().response();
		 * assertThat(response.getStatusCode(), is(equalTo(200)));
		 */
		
		given(reqSpec).get("/workspaces").then().spec(resSpec);

	}

	@Test
	public void getWorkspacesValidateBody() {

		//Response response = reqSpec.get("/workspaces").then().log().all().extract().response();
		
		/*
		 * Response response =
		 * given(reqSpec).get("/workspaces").then().log().all().extract().response();
		 * assertThat(response.getStatusCode(), is(equalTo(200)));
		 */
		
		Response response = given(reqSpec).get("/workspaces").then().spec(resSpec).extract().response();
		assertThat(response.path("workspaces[0].name").toString(), equalTo("Team Workspace"));

	}
	
	@Test
	public void queryTest() {
		QueryableRequestSpecification qr = SpecificationQuerier.query(reqSpec);
		System.out.println("The Base Uri ==> "+qr.getBaseUri());
	}

}