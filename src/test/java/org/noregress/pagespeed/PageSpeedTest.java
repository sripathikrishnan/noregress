package org.noregress.pagespeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;
import org.noregress.PageTester;
import org.noregress.Request;
import org.noregress.Result;
import org.noregress.Rule;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;


public class PageSpeedTest {

	@Ignore @Test
	public void testGithubUsingLiveService() {
		PageTester client = new PageSpeedOnlineV1Service("<YOUR-PAGE-SPEED-API-KEY>");
		Result result = client.testPage(new Request("http://github.com"));
		
		assertEquals("Http Response Code", 200, result.getResponseCode());
		assertTrue("Overall page score below threshold", result.getOverallScore() > 80);
		assertTrue("Too many resources on page", result.getNumberResources() < 35);
		assertTrue("Too many JS resources on page", result.getNumberJsResources() < 5);
		assertEquals("404's on page", 100, result.getScoreFor(Rule.AvoidBadRequests));
		assertTrue("Poor cache headers on external resources", result.getScoreFor(Rule.LeverageBrowserCaching) > 95);
		assertTrue("Minify Javascript", result.getScoreFor(Rule.MinifyJavaScript) > 95);
		assertEquals("Same image/css/js served from different URLs", 100, result.getScoreFor(Rule.ServeResourcesFromAConsistentUrl));
		assertEquals("Images are being scaled in html/css", 100, result.getScoreFor(Rule.ServeScaledImages));
		
		//assertTrue("Sprite Images, or use data:uri", result.getScoreFor(Rule.SpriteImages) > 80);
	}
	
	@Test
	public void testHomePage() {
		PageSpeedOnlineV1Service client = new PageSpeedOnlineV1Service("dummykey") {
			public InputStream getRawResponse(PageSpeedRequest request) throws IOException {
				return this.getClass().getClassLoader().getResourceAsStream("github.json");
			}
		};
		
		Result result = client.testPage(new Request("http://github.com"));
		assertEquals("Http Response Code", 200, result.getResponseCode());
		assertEquals("Overall Score", 84, result.getOverallScore());
		assertEquals("Number of Resources", 34, result.getNumberResources());
		assertEquals("Number of JS Resources", 5, result.getNumberHosts());
		
		assertEquals(48, result.getScoreFor(Rule.DeferParsingJavaScript));
		assertEquals(3.154, result.getImpactFor(Rule.DeferParsingJavaScript), 0.1d);
		
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
