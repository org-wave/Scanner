package org.wave.scanner.validators;

import java.util.Set;

import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.structures.Structure;


public class ConfigurationValidator {

	public void validate(Configuration configuration) throws ScannerException {
		String domain = configuration.getDomain();

		boolean hasDomain = domain != null && !domain.isEmpty();

		boolean hasPackages = false;
		Set<Structure> structures = configuration.getStructures();
		for (Structure structure : structures) {
			if (!structure.isEmpty()) {
				hasPackages = true;
				break;
			}
		}

		if (!hasDomain && !hasPackages) {
			throw new ScannerException(ErrorEnum.INVALID_CONFIGURATION);
		}
	}

}
