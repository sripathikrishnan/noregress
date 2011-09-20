package org.noregress.pagespeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.noregress.Request;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;


public class PageSpeedOnlineV1ServiceTest {

	@Test
	public void testHomePage() {
		PageSpeedOnlineV1Service client = new PageSpeedOnlineV1Service("dummykey") {
			public InputStream getRawResponse(PageSpeedRequest request) throws IOException {
				return this.getClass().getClassLoader().getResourceAsStream("github.json");
			}
		};
		
		PageSpeedResult result = client.testPage(new Request("http://github.com"));
		
		assertEquals("Http Response Code", 200, result.getResponseCode());
		assertEquals("Number of Resources", 34, result.getNumberResources());
		assertEquals("Number of JS Resources", 5, result.getNumberHosts());
		
		assertTrue("404s", result.getBadRequests().isEmpty());
		assertTrue("Including CSS files via @import", result.getCssIncludedViaImportDeclaration().isEmpty());
		assertTrue("Long Running Scripts", result.getLongRunningScripts().isEmpty());
		assertEquals("Scripts parsed at page load", 3, result.getScriptsParsedAtPageLoad().size());
	}
	
	@Test
	public void testGetServiceUrlWithDefaultStrategy() throws MalformedURLException {
		
		PageSpeedOnlineV1Service service = new PageSpeedOnlineV1Service("dummykey");
		
		PageSpeedRequest requestWithDefaultStrategy = 
			new PageSpeedRequest(new URL("http://pagetotest.com:8080/some/path.jsp?key1=value1"));
		
		assertEquals("https://www.googleapis.com/pagespeedonline/v1/runPagespeed?key=dummykey" +
				"&url=http%3A%2F%2Fpagetotest.com%3A8080%2Fsome%2Fpath.jsp%3Fkey1%3Dvalue1&strategy=desktop", 
				service.getServiceUrl(requestWithDefaultStrategy).toExternalForm());
	}
	
	@Test
	public void testGetServiceUrlWithMobileStrategy() throws MalformedURLException {
		
		PageSpeedOnlineV1Service service = new PageSpeedOnlineV1Service("dummykey");
		
		PageSpeedRequest requestWithMobileStrategy = 
			new PageSpeedRequest(new URL("http://pagetotest.com:8080/some/path.jsp?key1=value1"), Strategy.MOBILE);
		
		assertEquals("https://www.googleapis.com/pagespeedonline/v1/runPagespeed?key=dummykey" +
				"&url=http%3A%2F%2Fpagetotest.com%3A8080%2Fsome%2Fpath.jsp%3Fkey1%3Dvalue1&strategy=mobile", 
				service.getServiceUrl(requestWithMobileStrategy).toExternalForm());
		
	}
}
