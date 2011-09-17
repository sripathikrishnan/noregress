package org.noregress.pagespeed;

import org.json.JSONException;
import org.json.JSONObject;

import org.noregress.Rule;
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

	public int getOverallScore() {
		return score;
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
	
	public int getScoreFor(Rule rule) {
		return findRule(rule).getRuleScore();
	}
	
	public double getImpactFor(Rule rule) {
		return findRule(rule).getRuleImpact();
	}
	
	private RuleResult findRule(Rule rule) {
		RuleResult ruleResult = formattedResults.getRuleResults().get(rule.name());
		if(ruleResult == null) {
			throw new IllegalArgumentException("Invalid Rule " + rule);
		}
		return ruleResult;
	}
}
