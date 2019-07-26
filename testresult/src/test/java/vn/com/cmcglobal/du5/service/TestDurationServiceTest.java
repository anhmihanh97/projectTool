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
public class TestDurationServiceTest {
	@Autowired
	private TestDurationService durationService;

	@Test
	public void test_validateDuration_null() {
		assertFalse(durationService.validateDuration(null));
	}

	@Test
	public void test_validateDuration_empty() {
		assertFalse(durationService.validateDuration(""));
	}

	@Test
	public void test_validateDuration_space() {
		assertFalse(durationService.validateDuration(" "));
	}

	@Test
	public void test_validateDuration_lessThanOne() {
		assertFalse(durationService.validateDuration("0.5"));
	}

	@Test
	public void test_validateDuration_greaterThanOne() {
		assertTrue(durationService.validateDuration("5"));
	}
}
