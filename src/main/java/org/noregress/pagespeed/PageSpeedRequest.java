package org.noregress.pagespeed;

import java.net.URL;
import org.noregress.Request;

class PageSpeedRequest extends Request {

	private final Strategy strategy;
	
	PageSpeedRequest(URL url) {
		this(url, Strategy.DESKTOP);
	}
	PageSpeedRequest(URL url, Strategy strategy) {
		super(url);
		this.strategy = strategy;
	}
	
	Strategy getStrategy() {
		return strategy;
	}
}
