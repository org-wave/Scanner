package org.wave.scanner.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorEnum {
	
	NULL_XHTML("error.message.nullXHTML"),
	NULL_PATH("error.message.nullPath"),
	NULL_LIST("error.message.nullList"),
	NULL_FILE("error.message.nullFile"),
	INVALID_FILE("error.message.invalidFile"),
	EMPTY_FILE("error.message.emptyFile"),
	NULL_FOLDER("error.message.nullFolder"), 
	NULL_DOMAIN("error.message.nullDomain"), 
	NULL_PROJECT("error.message.nullProject"), 
	EMPTY_DOMAIN("error.message.emptyDomain"), 
	EMPTY_PROJECT("error.message.emptyProject"),
	NON_ERASABLE_CONTENT("error.message.nonErasableContent"),
	FOLDER_NOT_CREATED("error.message.folderNotCreated"),
	INVALID_NUMBER_OF_ARGUMENTS("error.message.invalidNumberOfArguments"), 
	FOLDER_NOT_FOUND("error.message.folderNotFound"), 
	INVALID_FOLDER("error.message.invalidFolder"),
	INVALID_CONFIGURATION("error.message.invalidConfiguration"),
	CONFIGURATION_FILE_NOT_FOUND("error.message.configurationFileNotFound"),
	INVALID_CONFIGURATION_FILE("error.message.invalidConfigurationFile"),
	PACKAGE_NOT_FOUND("error.message.packageNotFound"),
	NULL_STRUCTURE_TYPE("error.message.nullStructureType"), 
	EMPTY_PACKAGE("error.message.emptyPackage"), 
	INVALID_FILENAME("error.message.invalidFilename"),
	FILE_NOT_FOUND("error.message.fileNotFound");
	
//	TODO Rever todas as mensagens de erro.
	private String key;

	private ErrorEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("org.wave.scanner.messages.messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}

}
