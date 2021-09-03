package com.learning.workspaceapis;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.config.LogConfig;

public class GetWorkplaceBlacklistHeader {
	
	@Test
	public void getWorkspacesAsResponseVariable() {
		given().config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).
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

}
