package org.wave.scanner.receivers;

import java.io.File;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.utils.file.FileUtil;


public class DocsSetter {

	private String[] arguments;

	public void setDocs(Scanner scanner) throws ScannerException {
		File docs = new File(this.arguments[2]);

		if (docs.exists() && !FileUtil.delete(docs)) {
			throw new ScannerException(ErrorEnum.NON_ERASABLE_CONTENT);
		}

		if (!docs.mkdir()) {
			throw new ScannerException(ErrorEnum.FOLDER_NOT_CREATED, docs.getName());
		}

		scanner.setDocs(docs);
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

}
