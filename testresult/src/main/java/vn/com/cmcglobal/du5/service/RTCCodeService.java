package vn.com.cmcglobal.du5.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import vn.com.cmcglobal.du5.model.TicketType;

@Service
public class RTCCodeService {
	public boolean verifyRtcCode(String rtcCode, String status, Map<String, String> mRTCCode) {
		if ("OK".equals(status) || StringUtils.isEmpty(status)) {
			return StringUtils.isEmpty(rtcCode);
		} else if ("NG".equals(status) || "PN(Bug)".equalsIgnoreCase(status) || "PN(Internal)".equalsIgnoreCase(status)
				|| "PN(equipment)".equalsIgnoreCase(status) || "PN(QA)".equalsIgnoreCase(status)) {
			if (checkFormatRTC(rtcCode)) {
				if ("NG".equals(status) || "PN(Bug)".equalsIgnoreCase(status)) {
					if (mRTCCode.containsKey(rtcCode)) {
						return mRTCCode.get(rtcCode).equalsIgnoreCase(TicketType.DEFECT.getType());
					}
				}
				if ("PN(Internal)".equalsIgnoreCase(status)) {
					if (mRTCCode.containsKey(rtcCode)) {
						return mRTCCode.get(rtcCode).equalsIgnoreCase(TicketType.UPDATE_TC.getType());
					}
				}
				if ("PN(equipment)".equalsIgnoreCase(status)) {
					if (mRTCCode.containsKey(rtcCode)) {
						return mRTCCode.get(rtcCode).equalsIgnoreCase(TicketType.ENVIRONMENT.getType());
					}
				}

				if ("PN(QA)".equalsIgnoreCase(status)) {
					if (mRTCCode.containsKey(rtcCode)) {
						return mRTCCode.get(rtcCode).equalsIgnoreCase(TicketType.QA.getType());
					}
				}
			}
		} else {
			return checkFormatRTC(rtcCode);
		}
		return false;
	}

	public boolean checkFormatRTC(String rtcCode) {
		if (StringUtils.isEmpty(rtcCode)) {
			return false;
		}
		String regex = "^RT[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(rtcCode);
		return matcher.matches();
	}
}
