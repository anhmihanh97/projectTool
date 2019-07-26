package vn.com.cmcglobal.du5.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TestDurationService {
	public boolean validateDuration(String strTestDuration) {
		boolean isValid = false;
		if (StringUtils.isBlank(strTestDuration)) {
			return false;
		}
		if (strTestDuration.contains(".")) {
			return false;
		}
		int testDuration = Integer.parseInt(strTestDuration);
		if (testDuration > 1) {
			isValid = true;
		}
		return isValid;
	}
}
