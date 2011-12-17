package org.wave.scanner.commands;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.DocsFiller;


public class FillDocsCommand implements Command {

	@Inject
	private DocsFiller filler;

	@SuppressWarnings("unused")
	private FillDocsCommand() {

	}

	public FillDocsCommand(DocsFiller filler) {
		this.filler = filler;
	}

	@Override
	public void execute(Scanner scanner) throws ScannerException {
		this.filler.fill(scanner);
	}

}
