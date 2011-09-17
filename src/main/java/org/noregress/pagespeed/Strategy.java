package org.noregress.pagespeed;

enum Strategy {
	DESKTOP("desktop"),
	MOBILE("mobile");
	
	private final String value;
	private Strategy(String v) {
		this.value = v;
	}
	
	@Override
	public String toString() {
		return value;
	}
}