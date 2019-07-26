package vn.com.cmcglobal.du5.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.com.cmcglobal.du5.AppTestResult;
import vn.com.cmcglobal.util.LanguageUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestResult.class })
@TestPropertySource(locations = "classpath:application.properties")
public class LanguageUtilsTest {
	@Test
	public void test_VN_1() {
		assertTrue(LanguageUtils.checkVNCharacter("ăn sáng"));
	}

	@Test
	public void test_VN_2() {
		assertFalse(LanguageUtils.checkVNCharacter("toi di o to"));
	}

	@Test
	public void test_VN_3() {
		assertTrue(LanguageUtils.checkVNCharacter("Tôi"));
	}

	@Test
	public void test_VN_4() {
		assertTrue(LanguageUtils.checkVNCharacter(" Ị"));
	}

	@Test
	public void test_VN_5() {
		assertFalse(LanguageUtils.checkVNCharacter("テスト結果"));
	}

	@Test
	public void test_VN_6() {
		assertFalse(LanguageUtils.checkVNCharacter("a テスト結果"));
	}

	@Test
	public void test_VN_7() {
		assertTrue(LanguageUtils.checkVNCharacter("ạ テスト結果"));
	}

	@Test
	public void test_VN_8() {
		assertTrue(LanguageUtils.checkVNCharacter("hello ạ テスト結果"));
	}

	@Test
	public void test_VN_9() {
		assertTrue(LanguageUtils.checkVNCharacter("9 ạ テスト結果"));
	}
}
