package org.wave.scanner.receivers;

import java.io.File;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.validators.SourceValidator;


public class SourcesSetter {

	private String[] arguments;

	@Inject
	private SourceValidator validator;

	@SuppressWarnings("unused")
	private SourcesSetter() {

	}

	public SourcesSetter(SourceValidator validator) {
		this.validator = validator;
	}

	public void setSources(Scanner scanner) throws ScannerException {
		File src = new File(this.arguments[0]);
		File test = new File(this.arguments[1]);

		this.validator.validate(src);
		this.validator.validate(test);

		scanner.setSrc(src);
		scanner.setTest(test);
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

}
