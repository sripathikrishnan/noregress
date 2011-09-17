package org.noregress.pagespeed;

import org.json.JSONException;
import org.json.JSONObject;

class PageStats {

	private final int numberResources;
	private final int numberHosts;
	private final long totalRequestBytes;
	private final int numberStaticResources;
	private final long htmlResponseBytes;
	private final long cssResponseBytes;
	private final long imageResponseBytes;
	private final long javascriptResponseBytes;
	private final long otherResponseBytes;
	private final int numberJsResources;
	private final int numberCssResources;
	
	PageStats(JSONObject json) throws JSONException{
		
		numberResources = json.getInt("numberResources");
		numberHosts = json.getInt("numberHosts");
		totalRequestBytes = getLong(json.getString("totalRequestBytes"));
		numberStaticResources = json.getInt("numberStaticResources");
		htmlResponseBytes = getLong(json.getString("htmlResponseBytes"));
		cssResponseBytes = getLong(json.getString("cssResponseBytes"));
		imageResponseBytes = getLong(json.getString("imageResponseBytes"));
		javascriptResponseBytes = getLong(json.getString("javascriptResponseBytes"));
		otherResponseBytes = getLong(json.getString("otherResponseBytes"));
		numberJsResources = json.getInt("numberJsResources");
		numberCssResources = json.getInt("numberCssResources");
	}

	
	private long getLong(String str) {
		long value;
		try {
			value = Long.parseLong(str);
		}
		catch(NumberFormatException nfe) {
			value = -1;
		}
		return value;
	}


	/**
	 * @return the numberResources
	 */
	public int getNumberResources() {
		return numberResources;
	}


	/**
	 * @return the numberHosts
	 */
	public int getNumberHosts() {
		return numberHosts;
	}


	/**
	 * @return the totalRequestBytes
	 */
	public long getTotalRequestBytes() {
		return totalRequestBytes;
	}


	/**
	 * @return the numberStaticResources
	 */
	public int getNumberStaticResources() {
		return numberStaticResources;
	}


	/**
	 * @return the htmlResponseBytes
	 */
	public long getHtmlResponseBytes() {
		return htmlResponseBytes;
	}


	/**
	 * @return the cssResponseBytes
	 */
	public long getCssResponseBytes() {
		return cssResponseBytes;
	}


	/**
	 * @return the imageResponseBytes
	 */
	public long getImageResponseBytes() {
		return imageResponseBytes;
	}


	/**
	 * @return the javascriptResponseBytes
	 */
	public long getJavascriptResponseBytes() {
		return javascriptResponseBytes;
	}


	/**
	 * @return the otherResponseBytes
	 */
	public long getOtherResponseBytes() {
		return otherResponseBytes;
	}


	/**
	 * @return the numberJsResources
	 */
	public int getNumberJsResources() {
		return numberJsResources;
	}


	/**
	 * @return the numberCssResources
	 */
	public int getNumberCssResources() {
		return numberCssResources;
	}
	
}
