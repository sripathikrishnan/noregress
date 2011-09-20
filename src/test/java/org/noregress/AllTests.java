package org.noregress;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	org.noregress.pagespeed.ParserTest.class,
	org.noregress.pagespeed.PageSpeedOnlineV1ServiceTest.class
})
public class AllTests {

}
