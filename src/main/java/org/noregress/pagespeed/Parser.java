package org.noregress.pagespeed;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

class Parser {

	PageSpeedResult parse(InputStream in) throws IOException {
		
		PageSpeedResult result;
		try {
			JSONTokener tokener = new JSONTokener(in);
			JSONObject json = new JSONObject(tokener);
			
			result = new PageSpeedResult(json);
		}
		catch (JSONException e) {
			throw new IllegalStateException("Invalid JSON ", e);
		}
		
		return result;
	}
}
