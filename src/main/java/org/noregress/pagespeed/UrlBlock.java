package org.noregress.pagespeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UrlBlock {

	private final FormattedMessage header;
	private final List<FormattedMessage> urls;
	
	UrlBlock(JSONObject json) throws JSONException {
	
		this.header = new FormattedMessage(json.getJSONObject("header"));
		
		List<FormattedMessage> tempUrls = new ArrayList<FormattedMessage>();
		
		if(json.has("urls")) {
			JSONArray rawUrls = json.getJSONArray("urls");
			for(int i=0; i<rawUrls.length(); i++) {
				JSONObject obj = rawUrls.getJSONObject(i);
				tempUrls.add(new FormattedMessage(obj.getJSONObject("result")));
			}
		}
		this.urls = Collections.unmodifiableList(tempUrls);
	
	}

	/**
	 * @return the header
	 */
	FormattedMessage getHeader() {
		return header;
	}

	/**
	 * @return the urls
	 */
	List<FormattedMessage> getUrls() {
		return urls;
	}
}
