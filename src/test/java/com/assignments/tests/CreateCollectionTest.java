package com.assignments.tests;

import java.util.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.assignments.complexpojo.*;
import com.assignments.complexpojo.Collection;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class CreateCollectionTest {
	
	@BeforeClass
	public void settingUp() {
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(201).expectContentType(ContentType.JSON);
		RestAssured.responseSpecification = responseSpecBuilder.build();
	}

	@Test
	public void createColletionPojo() {
		Header header = new Header("Content-Type", "application/json");
		List<Header> headerList = new ArrayList<>();
		headerList.add(header);

		Body body = new Body("raw", "{\"data\": \"123\"}");

		Request request = new Request("https://postman-echo.com/post", "POST", headerList, body,
				"This is a sample request");
		RequestRoot requestRoot = new RequestRoot("Sample Post Request", request);
		List<RequestRoot> requestListRoot = new ArrayList<RequestRoot>();
		requestListRoot.add(requestRoot);

		Folder folder = new Folder("This is a folder", requestListRoot);
		List<Object> folderList = new ArrayList<Object>();
		folderList.add(folder);

		Info info = new Info("Sample Collection 1", "This is just a sample collection",
				"https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

		Collection colletion = new Collection(info, folderList);
		CollectionRoot collectionRoot = new CollectionRoot(colletion);

		given().
		baseUri("https://api.getpostman.com").
		header("Content-Type", "application/json").
		header("x-api-key", "PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		body(collectionRoot).
		log().
		all().
		when().
		post("/collections").
		then().
		spec(responseSpecification).
		extract().
		as(CollectionRoot.class);

	}

}