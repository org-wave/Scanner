package org.wave.scanner.commands;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.Certifier;


public class CertifyConventionsCommand implements Command {

	@Inject
	private Certifier certifier;

	@SuppressWarnings("unused")
	private CertifyConventionsCommand() {

	}

	public CertifyConventionsCommand(Certifier certifier) {
		this.certifier = certifier;
	}

	@Override
	public void execute(Scanner scanner) throws ScannerException {
		this.certifier.certify(scanner);
	}

}
