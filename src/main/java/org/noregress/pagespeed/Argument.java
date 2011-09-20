package org.noregress.pagespeed;

import org.json.JSONException;
import org.json.JSONObject;

class Argument {

	private final ArgumentType type;
	private final String value;
	
	Argument(JSONObject json) throws JSONException {
		this.type = ArgumentType.valueOf(json.getString("type"));
		this.value = json.getString("value");
	}

	/**
	 * @return the type
	 */
	ArgumentType getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	String getValue() {
		return value;
	}
}

