package com.testdriven.stormpath;

import static com.jayway.restassured.RestAssured.*;

import java.util.List;

public class Application extends AbstractResource {
	
	public String name;
	public String description;
	public String status;
	private HRef tenant;
	private  HRef accounts;
	private  HRef groups;
	private  HRef accountStoreMappings;
	private  HRef loginAttempts;
	private  HRef passwordResetTokens;
	
	public static class Collection {
		List<Application> items;
	}
	
	public static Application findByName(String appNameToFind) {
		Tenant tenant = Tenant.findCurrent();
		List<Application> apps = all();
				
		for( Application app : apps) {
			if( app.name.equals(appNameToFind)) {
				return app;
			}
		}
		return null;
	}

	/* "https://api.stormpath.com/v1/tenants/$TENANT_ID/applications?offset=10&limit=40" */
	private static List<Application> all() {
		List<Application> apps = expect().statusCode(200).when()
				.get(Tenant.findCurrent().applications.href + "?offset=0&limit=100")
				.as(Application.Collection.class).items;
		return apps;
	}
	
	public static void deleteAll() {
		for( Application each : all()) {
			System.out.println("deleting: " + each.name);
			if(each.name.equals("Stormpath")) continue;
			each.delete();
		}
	}
	
	public void delete() {
		expect().statusCode(204).when().delete(href);
	}

	public void save() {
		given().log().all()
		//TODO properly serialize
		.body("{ \"name\": \"" + name + "\"}")
		.expect().statusCode(201)
		.when().log().all().post("/applications");
		
	}

}

/*
 * {
            "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp",
            "name": "app513tVEKgxIXWS17pGUln6hq5xJEGmmciFLgQ2IQ9UwyEQ",
            "description": "app_5_Desc",
            "status": "ENABLED",
            "tenant": {
                "href": "https://api.stormpath.com/v1/tenants/6pjIXYgnMXa1fy1maxlqWr"
            },
            "defaultAccountStoreMapping": null,
            "defaultGroupStoreMapping": null,
            "accounts": {
                "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp/accounts"
            },
            "groups": {
                "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp/groups"
            },
            "accountStoreMappings": {
                "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp/accountStoreMappings"
            },
            "loginAttempts": {
                "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp/loginAttempts"
            },
            "passwordResetTokens": {
                "href": "https://api.stormpath.com/v1/applications/1787lB6pUH52Ygy6uL0Bgp/passwordResetTokens"
            }
        }
    */
