package com.learning.workspaceapis;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetWorkspaceHamcrestMatchersExamples {
	
	
	@Test
	public void getWorkspacesAsStringVariable() {
		given().
		baseUri("https://api.getpostman.com").
		header("x-api-key","PMAK-610cb897c0815d0052e4a990-3fe54408dbdba02303b2b610c32e835994").
		when().
		get("/workspaces").
		then().
		assertThat().
		statusCode(200).
		body("workspaces.name",contains("Team Workspace","My Workspace","New Personal Workspace","New Workspace"),
				"workspaces.name",containsInAnyOrder("My Workspace","Team Workspace","New Personal Workspace","New Workspace"),
				"workspaces.name",is(not(emptyArray())),
				"workspaces.name",hasSize(4),
				"workspaces[0]",hasKey("id"),
				"workspaces[0]",hasValue("Team Workspace"),
				"workspaces[0]",hasEntry("type", "team"));
		
	}

}