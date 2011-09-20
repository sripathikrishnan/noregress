package org.noregress.pagespeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FormattedMessage {

	private final String format;
	private final List<Argument> args;
	
	FormattedMessage(JSONObject json) throws JSONException {
		
		this.format = json.getString("format");
		
		List<Argument> tempArguments = new ArrayList<Argument>();
		if(json.has("args")) {
			JSONArray rawArgs = json.getJSONArray("args");
			for(int i=0; i<rawArgs.length(); i++) {
				JSONObject obj = rawArgs.getJSONObject(i);
				tempArguments.add(new Argument(obj));
			}
		}
		this.args = Collections.unmodifiableList(tempArguments);
	
	}

	/**
	 * @return the format
	 */
	String getFormat() {
		return format;
	}

	/**
	 * @return the args
	 */
	List<Argument> getArgs() {
		return args;
	}
	
}
