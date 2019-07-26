package vn.com.cmcglobal.du5.service;

import org.springframework.stereotype.Service;

import vn.com.cmcglobal.util.LanguageUtils;

@Service
public class TesterRemarkService {
	public boolean validateTesterRemark(String testerRemark) {

		return LanguageUtils.checkVNCharacter(testerRemark);

	}
}
