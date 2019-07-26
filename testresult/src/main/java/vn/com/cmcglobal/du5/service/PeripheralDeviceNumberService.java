package vn.com.cmcglobal.du5.service;

import java.util.Date;

public class PeripheralDeviceNumberService {
	private static final String TYFA = "【TYFA】システムテスト_";
	private static final String TZAA = "TZAA 【TZAA】システムテスト_";
	private static final String T20A = "【T20A】システムテスト_";
	public static final String END_NAME = "向け_保証型_CMC_持込み機器.xlsx_周辺機器";

	public boolean isPeripheralDeviceNumberService(String content) {

		// OK, NG, PN(Bug)
		if ("OK".equals(content) || "NG".equals(content) || "PN(Bug)".equals(content)) {
			return true;
		}
		// PN(QA)/PN(Internal)/PN(Equipment)/DG/DL/NT
		if ("PN(QN)".equals(content) || "PN(Internal)".equals(content) || "PN(Equipment)".equals(content)
				|| "DG".equals(content) || "DL".equals(content) || "NT".equals(content)) {
			return false;
		}

		return false;
	}

	public String checkMarket(String nameMarket, Date startST, Date endST, Date implementationDate, String nameST) {
		if (startST.before(endST) && startST.before(implementationDate) && implementationDate.before(endST)) {
			switch (nameMarket) {
			case "TYFA":
				return TYFA + nameST + END_NAME;
			case "TZAA":
				return TZAA + nameST + END_NAME;
			case "T20A":
				return T20A + nameST + END_NAME;

			}
		}
		return null;
	}
}
