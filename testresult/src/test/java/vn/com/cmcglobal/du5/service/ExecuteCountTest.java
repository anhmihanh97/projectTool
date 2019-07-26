package vn.com.cmcglobal.du5.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.com.cmcglobal.du5.AppTestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestResult.class })
@TestPropertySource(locations = "classpath:application.properties")
public class ExecuteCountTest {
	@Autowired
	public ExecuteCountService executeCouteservice;

	@Test
	public void ExecuteCountTestCase() {
		assertEquals(executeCouteservice.isValidExecuteCount("1", "1"), true);
	}

	@Test
	public void ExecuteCountTestCase1() {
		assertEquals(executeCouteservice.isValidExecuteCount("2", "1"), true);
	}

	@Test
	public void ExecuteCountTestCase2() {
		assertEquals(executeCouteservice.isValidExecuteCount("1", "2"), false);
	}

}
