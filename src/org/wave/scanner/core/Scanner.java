package org.wave.scanner.core;

import java.io.File;

import javax.enterprise.event.Observes;

import org.jboss.weld.environment.se.StartMain;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.wave.scanner.commands.Command;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.invokers.ScannerInvoker;


public class Scanner extends ScannerInvoker {

	// TODO Por log na execucao do scanner.
	private static final Integer NUMBER_OF_ARGUMENTS = 3;

	public static String[] arguments = StartMain.getParameters();

	private File src;

	private File test;

	private File docs;

	private Configuration configuration;

	public void scan(@Observes ContainerInitialized initialized) throws ScannerException {
		if (arguments.length != NUMBER_OF_ARGUMENTS) {
			throw new ScannerException(ErrorEnum.INVALID_NUMBER_OF_ARGUMENTS);
		}

		for (Command command : this.getCommands()) {
			command.execute(this);
		}
	}

	public File getSrc() {
		return src;
	}

	public void setSrc(File src) {
		this.src = src;
	}

	public File getTest() {
		return test;
	}

	public void setTest(File test) {
		this.test = test;
	}

	public File getDocs() {
		return docs;
	}

	public void setDocs(File docs) {
		this.docs = docs;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
