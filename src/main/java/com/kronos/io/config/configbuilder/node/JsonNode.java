package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class JsonNode extends Block {
	String jsonString;
	Class<?> type;

	public JsonNode(String name, String jsonString, Class<?> type) {
		super(false, name, new String[] { jsonString });
		this.jsonString = jsonString;
		this.type = type;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting JSON string
		int jsonStartIndex = val.indexOf("\"") + 1;
		int jsonEndIndex = val.lastIndexOf("\"");
		this.jsonString = val.substring(jsonStartIndex, jsonEndIndex);

		// Extracting type
		int typeStartIndex = val.indexOf("type=") + 5;
		String typeString = val.substring(typeStartIndex).trim();
		try {
			this.type = Class.forName(typeString);
		} catch (ClassNotFoundException e) {

		}
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + jsonString + "\";" + app + "type=" + type.getName() + ";";
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
}
