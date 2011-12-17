package org.wave.scanner.validators;

import java.io.File;

import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;


public class SourceValidator {

	public void validate(File sourceFolder) throws ScannerException {
		if (sourceFolder == null) {
			throw new ScannerException(ErrorEnum.NULL_FOLDER);
		}

		if (!sourceFolder.exists()) {
			throw new ScannerException(ErrorEnum.FOLDER_NOT_FOUND,sourceFolder.getName());
		}

		if (!sourceFolder.isDirectory()) {
			throw new ScannerException(ErrorEnum.INVALID_FOLDER,sourceFolder.getName());
		}
	}

}
