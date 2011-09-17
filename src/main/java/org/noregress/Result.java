package org.noregress;

import org.noregress.Rule;

public interface Result {

	int getResponseCode();
	
	int getOverallScore();
	
	
	/**
	 * @return the numberResources
	 */
	int getNumberResources();

	/**
	 * @return the numberHosts
	 */
	int getNumberHosts();

	/**
	 * @return the totalRequestBytes
	 */
	long getTotalRequestBytes();


	/**
	 * @return the numberStaticResources
	 */
	int getNumberStaticResources();


	/**
	 * @return the htmlResponseBytes
	 */
	long getHtmlResponseBytes();


	/**
	 * @return the cssResponseBytes
	 */
	long getCssResponseBytes();


	/**
	 * @return the imageResponseBytes
	 */
	long getImageResponseBytes();


	/**
	 * @return the javascriptResponseBytes
	 */
	long getJavascriptResponseBytes();


	/**
	 * @return the otherResponseBytes
	 */
	long getOtherResponseBytes();


	/**
	 * @return the numberJsResources
	 */
	int getNumberJsResources();


	/**
	 * @return the numberCssResources
	 */
	int getNumberCssResources();
	
	int getScoreFor(Rule rule);
	
	double getImpactFor(Rule rule);
	
}
