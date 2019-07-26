package vn.com.cmcglobal.du5;

import java.util.Date;

import vn.com.cmcglobal.util.DateUtils;

public class Constants {
	public static final String DESKTOP = System.getProperty("user.home") + "\\Desktop";
	public static final String WRONG_SHEET = "Reviewed Result";
	public static final String REVIEW_FILE = "Reviewed Result.xlsx";
	public static final String CORRECTION_REQUEST = "○";
	public static final Date DATE_CHECK_RTC = DateUtils.date(2019, 7, 24);
}
