package org.noregress;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;
import org.noregress.pagespeed.PageSpeedOnlineV1Service;

import static junit.framework.Assert.*;

public class GithubPerformanceTest {

	@Ignore @Test
	public void testGithubHomePageInAnonymousMode() {
		PageTester tester = new PageSpeedOnlineV1Service("<INSERT-PAGE-SPEED-API-KEY>");
		Result result = tester.testPage(new Request("http://github.com/"));
		
		/*
		 * Basic Assertions
		 */
		assertEquals("Http Response Code", 200, result.getResponseCode());
		assertEquals("404's on page", 0, result.getBadRequests().size());
		assertTrue("Too many domains used on the page", result.getNumberHosts() < 6);
		
		/*
		 * If somebody adds a new image or a new css/js file, these tests will catch them
		 */
		assertTrue("Too many resources on page", result.getNumberResources() < 35);
		assertTrue("Too many JS resources on page", result.getNumberJsResources() < 5);
		assertTrue("Too many CSS resources on page", result.getNumberCssResources() < 2);
		
		
		/*
		 * Github doesn't compress SVG files, when they should.
		 * But just to demonstrate the flexibility of Noregress, 
		 * we will ignore svg files as part of this test 
		 */
		List<URI> resourcesWithoutGzip = result.getResourcesWithoutCompression();
		List<URI> filteredResources = ignoreURIPatterns(resourcesWithoutGzip, "^.*svg$");
		assertTrue("GZip not enabled on some ", filteredResources.isEmpty());

		/*
		 * Several other assertions are possible. 
		 * Look at org.noregress.Result and org.noregress.pagespeed.PageSpeedResult
		 */
	}

	/*
	 * Okay this method shoudln't exist. Just use Guava or any other decent Collections library
	 */
	private List<URI> ignoreURIPatterns(List<URI> resources, String regex) {
		List<URI> filteredResources = new ArrayList<URI>();
		
		Pattern pattern = Pattern.compile(regex);
		for(URI resource : resources) {
			if(!pattern.matcher(resource.toString()).matches()) {
				filteredResources.add(resource);
			}
		}
		return filteredResources;
	}
}
