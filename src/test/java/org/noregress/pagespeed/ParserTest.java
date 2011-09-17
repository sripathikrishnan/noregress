package org.noregress.pagespeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import static junit.framework.Assert.*;

public class ParserTest {

	@Test
	public void testParsing() throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("github.json");
		assertNotNull(in);
		
		Parser parser = new Parser();
		PageSpeedResult result = parser.parse(in);
		
		assertNotNull(result);
		assertEquals("https://github.com/", result.getId());
		assertEquals(200, result.getResponseCode());
		assertEquals("Secure source code hosting and collaborative development - GitHub", result.getTitle());
		assertEquals(84, result.getOverallScore());
		
		assertPageStats(result.getPageStats());
		assertRules(result.getFormattedResults());
	}

	private void assertPageStats(PageStats pageStats) {
		assertEquals(34, pageStats.getNumberResources());
		assertEquals(5, pageStats.getNumberHosts());
		assertEquals(3461, pageStats.getTotalRequestBytes());
		assertEquals(30, pageStats.getNumberStaticResources());
		assertEquals(20253, pageStats.getHtmlResponseBytes());
		assertEquals(366447, pageStats.getCssResponseBytes());
		assertEquals(154307, pageStats.getImageResponseBytes());
		assertEquals(415371, pageStats.getJavascriptResponseBytes());
		assertEquals(583, pageStats.getOtherResponseBytes());
		assertEquals(4, pageStats.getNumberJsResources());
		assertEquals(1, pageStats.getNumberCssResources());
	}
	
	private void assertRules(FormattedResults formattedResults) {
		assertEquals("en_US", formattedResults.getLocale());
		Map<String, RuleResult> results = formattedResults.getRuleResults();
		
		assertEquals(28, results.size());
		
		RuleResult deferParsingJavaScript = results.get("DeferParsingJavaScript"); 
		assertEquals("Defer parsing of JavaScript", deferParsingJavaScript.getLocalizedRuleName());
		assertEquals(48, deferParsingJavaScript.getRuleScore());
		assertEquals(3.1542d, deferParsingJavaScript.getRuleImpact(), 0.1d);
	}
}
