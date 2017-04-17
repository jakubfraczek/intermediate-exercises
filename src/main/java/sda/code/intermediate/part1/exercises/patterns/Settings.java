package sda.code.intermediate.part1.exercises.patterns;

import java.util.Properties;

import sda.code.intermediate.FileUtils;

public enum Settings {
	CONFIG;

	private final Properties props;

	private Settings() {
		props = new FileUtils().loadDefaultProperties();
	}

	public String getString(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		} else {
			return value;
		}
	}

	public int getInteger(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		} else {
			return Integer.parseInt(value);
		}
	}

	public boolean getBoolean(String name) {
		String value = props.getProperty(name);
		if (value == null) {
			throw new SettingMissing(name);
		} else {
			return Boolean.parseBoolean(value);
		}
			}

	public int getInteger(String name, int defaultValue) {
		String value = props.getProperty(name);
		if (value == null) {
			return defaultValue;
		} 
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		String value = props.getProperty(name);
		if (value == null) {
			return defaultValue;
		} 
		return Boolean.parseBoolean(value);
	}

	public String getString(String name, String defaultValue) {
		if (props.containsKey(name)){
			return props.getProperty(name);
		}
		return defaultValue;
	}

}
