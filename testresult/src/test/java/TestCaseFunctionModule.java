import org.junit.Test;

import junit.framework.TestCase;
import vn.com.cmcglobal.util.FunctionAction;

public class TestCaseFunctionModule extends TestCase {
	private static FunctionAction acc = new FunctionAction();

//test checkExecute()
	@Test
	public void testcheckExecute() {

		assertEquals(acc.checkExecute("", null), "1");
	}

	@Test
	public void testcheckExecute1() {

		assertEquals(acc.checkExecute("1", "abc"), "2,3");
	}

	@Test
	public void testcheckExecute2() {

		assertEquals(acc.checkExecute("1", "2"), "2");
	}

	@Test
	public void testcheckExecute3() {

		assertEquals(acc.checkExecute("2", "1"), null);
	}

	@Test
	public void testcheckExecute4() {

		assertEquals(acc.checkExecute("-1", "-2"), "2");
	}

	@Test
	public void testcheckExecute5() {

		assertEquals(acc.checkExecute("1", "-2"), "2");
	}

// Test function checkLatestResult()
	@Test
	public void testCheckLatestResult() {
		assertEquals(acc.checkLatestResult("NG"), null);
	}

	@Test
	public void testCheckLatestResult1() {
		assertEquals(acc.checkLatestResult("aa"), "No format for Latest Result");
	}

}
