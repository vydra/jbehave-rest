package com.testdriven.stormpath;

import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.RestAssured.*;

public class Tenant extends AbstractResource {

	public static Tenant findCurrent() {
		given().   log().all().
	     expect().   log().all().
	          statusCode(200).
	          header("Set-Cookie", startsWith("rememberMe=deleteMe; Path=/; Max-Age=0; Expires=")).
	          body("name", equalTo( "foo" )).
	     when().
	       get("/tenants/current");
		return null;
	}

}
