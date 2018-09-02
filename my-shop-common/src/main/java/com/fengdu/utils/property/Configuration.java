package com.fengdu.utils.property;

public interface Configuration {
	String getProperty(String propertyName);

	String getProperty(String propertyName, String defaultValue);
}
