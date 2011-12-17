package org.wave.scanner.commands;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;


public interface Command {

//	TODO implementar para log
//	String getInfo();
	
	void execute(Scanner scanner) throws ScannerException;
	
	
}
