package com.testdriven.stormpath;

import static com.jayway.restassured.RestAssured.expect;

public class Tenant extends AbstractResource {

	public String name;
	public String key;
	HRef applications;

	public static Tenant findCurrent() {
		Tenant tenant = expect().statusCode(200).when().get("/tenants/current")
				.as(Tenant.class);
		return tenant;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [name=");
		builder.append(name);
		builder.append(", key=");
		builder.append(key);
		builder.append(", applications=");
		builder.append(applications);
		builder.append(", href=");
		builder.append(href);
		builder.append("]");
		return builder.toString();
	}

	

}
