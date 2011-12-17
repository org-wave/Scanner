package org.wave.scanner.commands;

import java.io.File;

import javax.inject.Inject;

import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.ConfigurationSetter;


public class SetConfigurationCommand implements Command {

	public static final String CONFIGURATION_FILE = File.separator + "META-INF" + File.separator + "scanner.xml";

	@Inject
	private ConfigurationSetter setter;

	@SuppressWarnings("unused")
	private SetConfigurationCommand() {

	}

	public SetConfigurationCommand(ConfigurationSetter setter) {
		this.setter = setter;
	}

	@Override
	public void execute(Scanner scanner) throws ScannerException {
		String pathName = scanner.getSrc().getPath() + CONFIGURATION_FILE;
		File configurationFile = new File(pathName);

		this.setter.validateConfigurationFile(configurationFile);
		Configuration configuration = this.setter.loadConfiguration(configurationFile);
		this.setter.setConfiguration(configuration, scanner);
	}

}
