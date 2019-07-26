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
public class EvaluationSoftwareServiceTest {
	@Autowired
	private EvaluationSoftwareService evaluationSoftwareService;

//   @Test
//   public void EvaluationSoftwareTestCase() throws ParseException {
//       Date sdate=new SimpleDateFormat("dd-MM-yyyy").parse("13-7-2019");
//    Date edate=new SimpleDateFormat("dd-MM-yyyy").parse("13-8-2019");
//   Date idate=new SimpleDateFormat("dd-MM-yyyy").parse("17-7-2019");
//	   assertEquals("","");
//   }
	@Test
	public void EvaluationSoftwareTestCase1() {
		assertTrue(evaluationSoftwareService.isInvalidEvaluationSoftware("NG"));
	}

	@Test
	public void EvaluationSoftwareTestCase2() {
		assertTrue(evaluationSoftwareService.isInvalidEvaluationSoftware("OK"));
	}

	@Test
	public void EvaluationSoftwareTestCase3() {
		assertFalse(evaluationSoftwareService.isInvalidEvaluationSoftware("PN(QN)"));
	}

}
