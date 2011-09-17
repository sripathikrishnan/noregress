package org.noregress.pagespeed;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

class FormattedResults {

	private final String locale;
	private final Map<String, RuleResult> ruleResults;
	
	FormattedResults(JSONObject json) throws JSONException {
		locale = json.getString("locale");
		ruleResults = Collections.unmodifiableMap(
				parseRuleResults(json.getJSONObject("ruleResults")));
	}

	private Map<String, RuleResult> parseRuleResults(JSONObject json) throws JSONException {
		String ruleNames[] = JSONObject.getNames(json);
		Map<String, RuleResult> ruleResults = new HashMap<String, RuleResult>(ruleNames.length);
		
		for(String ruleName : ruleNames) {
			JSONObject ruleObj = json.getJSONObject(ruleName);
			ruleResults.put(ruleName, new RuleResult(ruleName, ruleObj));
		}
		
		return ruleResults;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @return the ruleResults
	 */
	public Map<String, RuleResult> getRuleResults() {
		return ruleResults;
	}
}
