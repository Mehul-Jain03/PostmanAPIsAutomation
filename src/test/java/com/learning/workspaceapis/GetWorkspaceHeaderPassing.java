package com.learning.workspaceapis;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class GetWorkspaceHeaderPassing {
	
	@Test
	public void headerPassMethod1() {
		
		// Passing 2 Headers one by one
		
		given().
		baseUri("https://api.getpostman.com").
		header("content-type","application/json").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200);
		
		
	}
	
	@Test
	public void headerPassMethod2() {
		
		// Passing 2 Headers in one go headers method
		
		given().
		baseUri("https://api.getpostman.com").
		headers("content-type","application/json","x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200);
		
		
	}
	
	@Test
	public void headerPassMethod3() {
		
		Header headerOne = new Header("content-type","application/json");
		Header headerTwo = new Header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		
		given().
		baseUri("https://api.getpostman.com").
		header(headerOne).
		header(headerTwo).
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200);
		
		
	}
	
	@Test
	public void headerPassMethod4() {
		
		Header headerOne = new Header("content-type","application/json");
		Header headerTwo = new Header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		
		Headers headers =  new Headers(headerOne,headerTwo);
		
		given().
		baseUri("https://api.getpostman.com").
		headers(headers).
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200);
		
	}
	
	@Test
	public void headerPassMethod5() {
		
		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("content-type","application/json");
		headersMap.put("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994");
		
		given().
		baseUri("https://api.getpostman.com").
		headers(headersMap).
		log().
		all().
		when().
		get("/workspaces").
		then().
		log().
		headers().
		assertThat().
		statusCode(200);
		
	}
	
	@Test(enabled = false)
	public void headerPassMethod6() {
		
		given().
		log().headers().
		baseUri("https://api.getpostman.com").
		header("multivalue","value1","value2").
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200);
		
	}
	
	@Test(enabled = false)
	public void headerPassMethod7() {
		
		Header headerOne = new Header("multivalue","value1");
		Header headerTwo = new Header("multivalue","value2");
		
		Headers headers =  new Headers(headerOne,headerTwo);
		
		given().
		log().headers().
		baseUri("https://api.getpostman.com").
		headers(headers).
		log().
		all().
		when().
		get("/workspaces").
		then().
		log().
		headers().
		assertThat().
		statusCode(200);
		
	}
	
	@Test
	public void headerPassMethod8() {
		
		given().
		log().headers().
		baseUri("https://api.getpostman.com").
		header("content-type","application/json").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		log().
		all().
		when().
		get("/workspaces").
		then().
		log().
		headers().
		assertThat().
		statusCode(200).
		header("Connection", "keep-alive").
		headers("Server","nginx","Connection","keep-alive");
	}
	
	@Test
	public void headerPassMethod9() {
		
		Headers extractedHeader =  given().
		log().headers().
		baseUri("https://api.getpostman.com").
		header("content-type","application/json").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).
		extract().
		headers();
		
		System.out.println("Header Name " + extractedHeader.get("x-frame-options").getName());
		System.out.println("Header Value " + extractedHeader.get("x-frame-options").getValue());
		System.out.println("Header Value " + extractedHeader.getValue("x-frame-options"));
		
	
	}
	
	@Test
	public void headerPassMethod10() {
		
		Headers extractedHeader =  given().
		log().headers().
		baseUri("https://api.getpostman.com").
		header("content-type","application/json").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		log().
		all().
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).
		extract().
		headers();
		
		for(Header header : extractedHeader) {
			System.out.println("Header Name is " + header.getName() + " And " + "Header Value is "+header.getValue() );
		}
	
	}

}