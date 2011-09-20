package org.noregress;

import java.net.URI;
import java.util.List;

/**
 * Represents the Result of a performance test on web page
 * 
 * Think of this interface as the result of running YSlow or Page Speed or WebPageTest.org on a given URL.
 * 
 * This interface encapsulates a common subset of metrics that are available across all tools. 
 * Any tool must support these metrics at a minimum. 
 * 
 * @author sri
 *
 */
public interface Result {

	/**
	 * Gets the Http Response code that was returned by the server under test
	 *  
	 * @return the Http response code returned by server under test
	 */
	int getResponseCode();
	
	/**
	 * The number of resources a page contains 
	 * This includes Css, Javascript, Images, Imported Fonts, Flash files, Appplets and so on.
	 * It also includes any AJAX calls that are made from the page.
	 *  
	 * @return the numberResources the total number of resources detected on a page
	 */
	int getNumberResources();

	/**
	 * The number of unique host names in a web page
	 * Each host name requires a DNS lookup, and so it is important to keep this number low
	 * 
	 * @return the numberHosts the number of unique domains/host names in a page
	 */
	int getNumberHosts();

	/**
	 * @return the totalRequestBytes
	 */
	long getTotalRequestBytes();


	/**
	 * The number of static resources i.e. css, javascript, images, fonts present on a page
	 * Comparing getNumberStaticResources and getNumberResources - the former excludes AJAX requests, the latter includes it
	 *     
	 * @return the numberStaticResources the number of static resources on a page
	 */
	int getNumberStaticResources();


	/**
	 * The uncompressed number of bytes for the html page
	 * @return the htmlResponseBytes
	 */
	long getHtmlResponseBytes();


	/**
	 * The uncompressed number of bytes for all css files combined
	 * @return the cssResponseBytes
	 */
	long getCssResponseBytes();


	/**
	 * The uncompressed number of bytes for all images on the page
	 * @return the imageResponseBytes
	 */
	long getImageResponseBytes();


	/**
	 * The uncompressed number of bytes for all javascript resources on the page
	 * @return the javascriptResponseBytes
	 */
	long getJavascriptResponseBytes();


	/**
	 * The uncompressed number of bytes for resources other than javascript/images/css/html
	 * @return the otherResponseBytes
	 */
	long getOtherResponseBytes();


	/**
	 * The number of javascript files on the page
	 * @return the numberJsResources
	 */
	int getNumberJsResources();


	/**
	 * The number of css files on a page
	 * @return the numberCssResources
	 */
	int getNumberCssResources();
	
	List<URI> getBadRequests();
	
	List<URI> getCssIncludedViaImportDeclaration();
	
	List<URI> getLongRunningScripts();
	
	List<URI> getScriptsParsedAtPageLoad();
}
