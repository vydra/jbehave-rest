package com.testdriven.stormpath;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TestConfig {
	
	static final File configFile = new File("/home/dvydra/.stormpath/apiKey.properties");
	
	static String readProp(String propName) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(configFile));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return (String) props.get(propName);
	}
	
	public static String getApiKey() {
		return readProp("apiKey.id");
		
	}

	public static String getApiSecret() {
		return readProp("apiKey.secret");
	}

}
