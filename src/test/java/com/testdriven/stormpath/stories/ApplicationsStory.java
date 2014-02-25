package com.testdriven.stormpath.stories;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.testdriven.stormpath.Application;
import com.testdriven.stormpath.Client;
import com.testdriven.stormpath.Tenant;

public class ApplicationsStory {
	
	@BeforeClass public static void
	connect() {
		Client.connect();
	}

	@Test public void 
	add_new_application() {
		given().current_tenant()
		.when()
		  .I_add_an_application_with_unique_name()
		.expect().success();
	}
	
	//*** Fixtures + Internal DSL ***
	
	Tenant _currentTenant;
	Application _app1;
	Result _res;
	
	ApplicationsStory given() {
		return this;
	}
	
	ApplicationsStory when() {
		return this;
	}
	
	public static class Result {
		boolean _success = false;
		Result setSuccess(boolean flag) {
			_success = flag;
			return this;
		}
		public Result success() {
			assertTrue(_success);
			return this;
		}
	}
	
	Result expect() {
		return _res;
	}
	
	ApplicationsStory current_tenant() {
		_currentTenant = Tenant.findCurrent();
		return this;
	}
	
	ApplicationsStory I_add_an_application_with_unique_name() {
		Application newApp = new Application();
		newApp.name = "New App" + System.currentTimeMillis();
		newApp.save();
		_res = new Result().setSuccess(true);
		return this;
	}
	

}
