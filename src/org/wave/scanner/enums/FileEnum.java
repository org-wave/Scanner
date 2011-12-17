package org.wave.scanner.enums;

public enum FileEnum {

	SRC_FOLDER("src"),
	TEST_FOLDER("test"),
	DOCS_FOLDER("docs"),
	CONFIGURATION_FILE("src/META-INF/scanner.xml");

	private String value;

	private FileEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
