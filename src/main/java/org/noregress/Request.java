package org.noregress;

import java.net.MalformedURLException;
import java.net.URL;

public class Request {
	
	private final URL pageUrl;
	
	public Request(String pageUrl) {
		this(createUrl(pageUrl));
	}

	public Request(URL pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	private static URL createUrl(String urlAsStr) {
		try {
			return new URL(urlAsStr);
		}
		catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid URL " + urlAsStr, e);
		}
	}
	
	public URL getPageUrl() {
		return pageUrl;
	}
}
