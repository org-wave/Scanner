package org.wave.scanner.receivers;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.InfoEnum;
import org.wave.scanner.structures.Structure;


public class PackageRemover {

	@Inject
	private Logger logger;

	@SuppressWarnings("unused")
	private PackageRemover() {

	}

	public PackageRemover(Logger logger) {
		this.logger = logger;
	}

	public void removePackages(Scanner scanner) {
		Set<Structure> structures = scanner.getConfiguration().getStructures();
		for (Structure structure : structures) {

			Set<String> packages = new HashSet<String>(structure.getPackages());
			for (String packageName : packages) {
				boolean containsPackageInSrc = this.contains(packageName, scanner.getSrc());
				boolean containsPackageInTest = this.contains(packageName, scanner.getTest());

				if (!containsPackageInSrc || !containsPackageInTest) {
					this.logger.info(InfoEnum.PACKAGE_NOT_FOUND.getMessage(packageName));
					structure.getPackages().remove(packageName);
				}
			}
		}
	}

	private boolean contains(String packageName, File source) {
		String pathName = packageName.replace(".", File.separator);

		File packageFile = new File(source, pathName);
		return packageFile.exists();
	}

}
