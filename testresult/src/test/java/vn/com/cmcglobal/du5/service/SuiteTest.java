package vn.com.cmcglobal.du5.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LatestResultTest.class, RTCCodeServiceTest.class, ExecuteCountTest.class, LanguageUtilsTest.class,
		TestDurationServiceTest.class })
public class SuiteTest {

}
