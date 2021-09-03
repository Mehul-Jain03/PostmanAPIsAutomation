package com.learning.workspaceapis;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class GetWorkspaceValidateResponseBody {
	
	
	@Test
	public void getWorkspacesAsResponseVariable() {
		given().
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		log().
		all().
		assertThat().
		statusCode(200).
		body("workspaces.name",hasItems("Team Workspace","My Workspace","New Personal Workspace"), 
				"workspaces.type",hasItems("team","personal","personal","team"),
				"workspaces[0].name",equalTo("Team Workspace"),
				"workspaces[0].name",is(equalTo("Team Workspace")),
				"workspaces.size()",equalTo(4),
				"workspaces.type",hasItem("personal"));
	
	}

}