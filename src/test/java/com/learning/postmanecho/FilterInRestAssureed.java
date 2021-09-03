package com.learning.postmanecho;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class FilterInRestAssureed {
	
	// This Class Explains the use of filter method to print the logs
	
	
	@Test
	public void filterUseWithPrintStream() throws FileNotFoundException {
		
		PrintStream ps = new PrintStream(new File ("restassured.log"));
		
		given().
		baseUri("https://postman-echo.com").
		filter(new RequestLoggingFilter(ps)).
		filter(new ResponseLoggingFilter(ps)).
		when().
		get("/get").
		then().
		assertThat().
		statusCode(200);
		
	}
	
	@Test
	public void filterUseWithDefaultFilter() {
		
		given().
		baseUri("https://postman-echo.com").
		filter(new RequestLoggingFilter(LogDetail.HEADERS)).
		filter(new ResponseLoggingFilter(LogDetail.BODY)).
		when().
		get("/get").
		then().
		assertThat().
		statusCode(200);
		
	}

}