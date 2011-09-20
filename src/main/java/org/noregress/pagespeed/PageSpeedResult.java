package org.noregress.pagespeed;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import org.noregress.pagespeed.Rule;
import org.noregress.Result;


class PageSpeedResult implements Result {
	
	private final String id;
	private final int responseCode;
	private final String title;
	
	private final int score;
	
	private final PageStats pageStats;
	private final FormattedResults formattedResults; 
	private final Version version;
	
	PageSpeedResult(JSONObject json) throws JSONException {
		id = json.getString("id");
		responseCode = json.getInt("responseCode");
		title = json.getString("title");
		score = json.getInt("score");
		pageStats = new PageStats(json.getJSONObject("pageStats"));
		version = new Version(json.getJSONObject("version"));
		formattedResults = new FormattedResults(json.getJSONObject("formattedResults"));
	}

	PageStats getPageStats() {
		return pageStats;
	}
	
	FormattedResults getFormattedResults() {
		return formattedResults;
	}

	Version getVersion() {
		return version;
	}

	String getId() {
		return id;
	}

	String getTitle() {
		return title;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public int getNumberResources() {
		return pageStats.getNumberResources();
	}

	public int getNumberHosts() {
		return pageStats.getNumberHosts();
	}

	public long getTotalRequestBytes() {
		return pageStats.getTotalRequestBytes();
	}

	public int getNumberStaticResources() {
		return pageStats.getNumberStaticResources();
	}

	public long getHtmlResponseBytes() {
		return pageStats.getHtmlResponseBytes();
	}

	public long getCssResponseBytes() {
		return pageStats.getCssResponseBytes();
	}

	public long getImageResponseBytes() {
		return pageStats.getImageResponseBytes();
	}

	public long getJavascriptResponseBytes() {
		return pageStats.getJavascriptResponseBytes();
	}

	public long getOtherResponseBytes() {
		return pageStats.getJavascriptResponseBytes();
	}

	public int getNumberJsResources() {
		return pageStats.getNumberJsResources();
	}

	public int getNumberCssResources() {
		return pageStats.getNumberCssResources();
	}

	public List<URI> getBadRequests() {
		RuleResult rule = findRule(Rule.AvoidBadRequests);
		return getListOfURI(rule.getUrlBlocks());
	}

	public List<URI> getCssIncludedViaImportDeclaration() {
		RuleResult rule = findRule(Rule.AvoidCssImport);
		return getListOfURI(rule.getUrlBlocks());
	}

	public List<URI> getLongRunningScripts() {
		RuleResult rule = findRule(Rule.AvoidLongRunningScripts);
		return getListOfURI(rule.getUrlBlocks());
	}

	public List<URI> getScriptsParsedAtPageLoad() {
		RuleResult rule = findRule(Rule.DeferParsingJavaScript);
		return getListOfURI(rule.getUrlBlocks());
	}

	private RuleResult findRule(Rule rule) {
		RuleResult ruleResult = formattedResults.getRuleResults().get(rule.name());
		if(ruleResult == null) {
			throw new IllegalArgumentException("Invalid Rule " + rule);
		}
		return ruleResult;
	}
	
	private List<URI> getListOfURI(List<UrlBlock> urlBlocks) {
		List<URI> uris = new ArrayList<URI>();
		
		if(!urlBlocks.isEmpty()) {
			//Haven't seen multiple UrlBlocks even through the schema allows it
			UrlBlock urlblock = urlBlocks.get(0);
			
			for(FormattedMessage message : urlblock.getUrls()) {
				uris.add(getURI(message));
			}
		}
		
		return uris;
	}

	private URI getURI(FormattedMessage message) {
		
		for(Argument arg : message.getArgs()) {
			if(arg.getType().equals(ArgumentType.URL)) {
				try {
					return new URI(arg.getValue());
				}
				catch (URISyntaxException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
		
		throw new IllegalArgumentException("Could not find a URI in the formatted message " + message);
	}

	public int getOverallScore() {
		return score;
	}

}
