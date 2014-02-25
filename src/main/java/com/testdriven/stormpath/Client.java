package com.testdriven.stormpath;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;

public class Client {
	public static void connect() {
		String username = TestConfig.getApiKey();
		String password = TestConfig.getApiSecret();

		RestAssured.baseURI = "https://api.stormpath.com";
		RestAssured.basePath = "/v1";
		RestAssured.authentication = basic(username, password);
		RestAssured.requestContentType("application/json");
		//connect by looking up current tenant
		Tenant.findCurrent();
	}

}
