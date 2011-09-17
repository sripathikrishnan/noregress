package org.noregress.pagespeed;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.noregress.PageTester;
import org.noregress.Request;

public class PageSpeedOnlineV1Service implements PageTester {
	
	private final String apiKey;
	private final Parser parser;
	
	public PageSpeedOnlineV1Service(String apiKey) {
		this.apiKey = apiKey;
		this.parser = new Parser();
	}

	public PageSpeedResult testPage(Request request) {
		PageSpeedResult result;
		
		PageSpeedRequest psr = new PageSpeedRequest(request.getPageUrl());
		InputStream resultStream;
		try {
			resultStream = getRawResponse(psr);
			result = parser.parse(resultStream);
		}
		catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
		return result;
	}
	
	InputStream getRawResponse(PageSpeedRequest request) throws IOException {
		URL url = getServiceUrl(request);
		return url.openStream();
	}
	
	URL getServiceUrl(PageSpeedRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("https://www.googleapis.com/pagespeedonline/v1/runPagespeed")
			.append("?")
			.append("key").append("=").append(encode(apiKey))
			.append("&")
			.append("url").append("=").append(encode(request.getPageUrl().toExternalForm()))
			.append("&")
			.append("strategy").append("=").append(request.getStrategy().toString());
			
		try {
			return new URL(builder.toString());
		}
		catch (MalformedURLException e) {
			throw new IllegalStateException("Bug in code - could not create a well formed url", e);
		}
	}
	
	private String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding does not exist?", e);
		}
	}
}