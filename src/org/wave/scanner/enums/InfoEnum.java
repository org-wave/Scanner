package org.wave.scanner.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum InfoEnum {
	
	PACKAGE_NOT_FOUND("info.message.packageNotFound"),
	DOCS_GENERATED("info.message.docsGenerated"),
	VALIDATE("info.message.validate"),
	SCAN("info.message.scan");
	
	private String key;

	private InfoEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("org.wave.scanner.messages.messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}

}
