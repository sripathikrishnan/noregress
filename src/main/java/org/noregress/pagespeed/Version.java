package org.noregress.pagespeed;

import org.json.JSONException;
import org.json.JSONObject;

class Version {

	private final int major;
	private final int minor;
	
	Version(JSONObject json) throws JSONException {
		major = json.getInt("major");
		minor = json.getInt("minor");
	}

	/**
	 * @return the major
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * @return the minor
	 */
	public int getMinor() {
		return minor;
	}
}
