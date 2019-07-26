package vn.com.cmcglobal.du5.service;

import org.springframework.stereotype.Service;

import vn.com.cmcglobal.util.LanguageUtils;

@Service
public class DescriptionService {
	public boolean validateDescription(String description) {
		return LanguageUtils.checkVNCharacter(description);
	}
}
