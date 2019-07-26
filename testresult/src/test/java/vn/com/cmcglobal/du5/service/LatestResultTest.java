package vn.com.cmcglobal.du5.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class LatestResultTest {
	@Autowired
	LatestResultService latestResultService;

	@Test
	public void test_empty_result() {
		assertTrue(latestResultService.isValidResult(""));
	}

	@Test
	public void test_blank_result() {
		assertFalse(latestResultService.isValidResult(" "));
	}

	@Test
	public void test_incorrect_result() {
		assertFalse(latestResultService.isValidResult("xxx"));
	}
}
