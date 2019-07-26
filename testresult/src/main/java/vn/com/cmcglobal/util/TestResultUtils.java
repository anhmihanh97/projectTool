package vn.com.cmcglobal.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class TestResultUtils {
	public static Integer getColumnIndexByName(Row headerRow, String text) {
		Integer index = null;
		for (int cn = 0; cn < headerRow.getLastCellNum(); cn++) {
			Cell c = headerRow.getCell(cn);
			if (text.equals(c.getStringCellValue())) {
				index = cn;
				break;
			}
		}
		return index;
	}

	public static boolean isBelongToCMC(String testerName) {
		if (StringUtils.isEmpty(testerName)) {
			return false;
		}
		return testerName.toLowerCase().contains("du5");
	}

	public static String getComponentFromAbsolutePath(String absolutePath) {
		int i = absolutePath.lastIndexOf("\\");
		return absolutePath.substring(i + 1);
	}
}
