package com.testdriven.stormpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TenantTest {
	
	@BeforeClass public static void
	connect() {
		Client.connect();
	}

	@Test public void 
	find_urrent() {
		Tenant tenant = Tenant.findCurrent();
		assertTrue(tenant.name.length() > 0);
	}
	
	//TODO move to ApplicationTest
	@Test public void
	find_application_by_name() {
		Tenant tenant = Tenant.findCurrent();
		Application appFound = Application.findByName("Stormpath");
		assertEquals("Stormpath", appFound.name);
	}
	

}
