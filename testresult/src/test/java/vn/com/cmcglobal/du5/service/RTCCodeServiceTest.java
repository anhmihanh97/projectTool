package vn.com.cmcglobal.du5.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.com.cmcglobal.du5.AppTestResult;
import vn.com.cmcglobal.du5.model.TicketType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestResult.class })
@TestPropertySource(locations = "classpath:application.properties")
public class RTCCodeServiceTest {
	@Autowired
	RTCCodeService rtcCodeService;

	private Map<String, String> mRTCCode;

	@Before
	public void initValue() {
		mRTCCode = new HashedMap<String, String>();
		mRTCCode.put("RT11111", TicketType.DEFECT.getType());
		mRTCCode.put("RT11112", TicketType.ENVIRONMENT.getType());
		mRTCCode.put("RT11113", TicketType.UPDATE_TC.getType());
	}

	@Test
	public void test_verifyStatus_OK() {
		assertTrue(rtcCodeService.verifyRtcCode("", "OK", mRTCCode));
	}

	@Test
	public void test_verifyStatus_OK2() {
		assertFalse(rtcCodeService.verifyRtcCode("RT11111", "OK", mRTCCode));
	}

	@Test
	public void test_verifyStatus_equipment() {
		assertTrue(rtcCodeService.verifyRtcCode("RT11112", "PN(Equipment)", mRTCCode));
	}

	@Test
	public void test_verifyStatus_equipment2() {
		assertFalse(rtcCodeService.verifyRtcCode("RT11113", "PN(equipment)", mRTCCode));
	}

	@Test
	public void test_verifyStatus_internal() {
		assertTrue(rtcCodeService.verifyRtcCode("RT11113", "PN(internal)", mRTCCode));
	}

	@Test
	public void test_verifyStatus_internal2() {
		assertFalse(rtcCodeService.verifyRtcCode("RT11114", "PN(internal)", mRTCCode));
	}

	@Test
	public void test_verifyStatus_DG() {
		assertTrue(rtcCodeService.verifyRtcCode("RT11113", "DG", mRTCCode));
	}

	@Test
	public void test_verifyStatus_DG2() {
		assertFalse(rtcCodeService.verifyRtcCode("R11113", "DG", mRTCCode));
	}
}
