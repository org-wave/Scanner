package org.wave.scanner.receivers;

import java.io.File;

import javax.inject.Inject;

import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.factories.ConfigurationFactory;
import org.wave.scanner.validators.ConfigurationValidator;


public class ConfigurationSetter {

	@Inject
	private ConfigurationValidator validator;

	@SuppressWarnings("unused")
	private ConfigurationSetter() {

	}

	public ConfigurationSetter(ConfigurationValidator validator) {
		this.validator = validator;
	}

	public void validateConfigurationFile(File configurationFile) throws ScannerException {
		if (!configurationFile.exists()) {
			throw new ScannerException(ErrorEnum.CONFIGURATION_FILE_NOT_FOUND);
		}

		if (configurationFile.isDirectory()) {
			throw new ScannerException(ErrorEnum.INVALID_CONFIGURATION_FILE);
		}
	}

	public Configuration loadConfiguration(File configurationFile) throws ScannerException {
		Configuration configuration = ConfigurationFactory.create(configurationFile);

		this.validator.validate(configuration);

		return configuration;
	}

	public void setConfiguration(Configuration configuration, Scanner scanner) {
		if (configuration.hasDomain()) {
			for (StructureEnum type : StructureEnum.values()) {
				type.addDefaultPackage(configuration);
			}
		}

		scanner.setConfiguration(configuration);
	}

}
