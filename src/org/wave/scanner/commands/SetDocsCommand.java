package org.wave.scanner.commands;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.DocsSetter;


public class SetDocsCommand implements Command {

	@Inject
	private DocsSetter setter;

	@SuppressWarnings("unused")
	private SetDocsCommand() {

	}

	public SetDocsCommand(DocsSetter setter) {
		this.setter = setter;
	}

	@Override
	public void execute(Scanner scanner) throws ScannerException {
		this.setter.setArguments(Scanner.arguments);
		this.setter.setDocs(scanner);
	}

}
