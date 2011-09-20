package org.noregress.pagespeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class RuleResult implements Comparable<RuleResult> {

	private final String id;
	private final String localizedRuleName;
	private final int ruleScore;
	private final double ruleImpact;
	private final List<UrlBlock> urlBlocks;

	RuleResult(String id, JSONObject json) throws JSONException {
		this.id = id;
		this.localizedRuleName = json.getString("localizedRuleName");
		this.ruleScore = json.getInt("ruleScore");
		this.ruleImpact = json.getDouble("ruleImpact");
		
		List<UrlBlock> tempUrlBlocks = new ArrayList<UrlBlock>();
		
		if(json.has("urlBlocks")) {
			JSONArray rawUrlBlocks = json.getJSONArray("urlBlocks");
			for(int i=0; i<rawUrlBlocks.length(); i++) {
				JSONObject obj = rawUrlBlocks.getJSONObject(i);
				tempUrlBlocks.add(new UrlBlock(obj));
			}
		}
		this.urlBlocks = Collections.unmodifiableList(tempUrlBlocks);
		
	}

	public int compareTo(RuleResult other) {
		int result;
		
		if(other == null) {
			result = 1;
		}
		else if(this.equals(other)) {
			result = 0;
		}
		else {
			long diff = Double.doubleToLongBits(this.ruleImpact) - 
						Double.doubleToLongBits(other.ruleImpact);
			
			result = (int)diff;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((localizedRuleName == null) ? 0 : localizedRuleName
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ruleImpact);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ruleScore;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleResult other = (RuleResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localizedRuleName == null) {
			if (other.localizedRuleName != null)
				return false;
		} else if (!localizedRuleName.equals(other.localizedRuleName))
			return false;
		if (Double.doubleToLongBits(ruleImpact) != Double
				.doubleToLongBits(other.ruleImpact))
			return false;
		if (ruleScore != other.ruleScore)
			return false;
		return true;
	}


	/**
	 * @return the id
	 */
	String getId() {
		return id;
	}


	/**
	 * @return the localizedRuleName
	 */
	String getLocalizedRuleName() {
		return localizedRuleName;
	}


	/**
	 * @return the ruleScore
	 */
	int getRuleScore() {
		return ruleScore;
	}


	/**
	 * @return the ruleImpact
	 */
	double getRuleImpact() {
		return ruleImpact;
	}

	/**
	 * @return the urlBlocks
	 */
	List<UrlBlock> getUrlBlocks() {
		return urlBlocks;
	}
}
