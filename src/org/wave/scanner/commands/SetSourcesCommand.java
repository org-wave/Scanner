package org.wave.scanner.commands;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.SourcesSetter;


public class SetSourcesCommand implements Command{

	@Inject
	private SourcesSetter setter;
	
	@SuppressWarnings("unused")
	private SetSourcesCommand() {
		
	}
	
	public SetSourcesCommand(SourcesSetter setter) {
		this.setter = setter;
	}

	@Override
	public void execute(Scanner scanner) throws ScannerException {
		this.setter.setArguments(Scanner.arguments);
		this.setter.setSources(scanner);		
	}

}
