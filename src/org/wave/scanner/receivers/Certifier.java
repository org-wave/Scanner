package org.wave.scanner.receivers;

import java.io.File;
import java.util.Set;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.structures.Structure;


public class Certifier {

	private static final String EXTENSION = ".java";

	public static final String SUFFIX = "Test" + EXTENSION;

	public void certify(Scanner scanner) throws ScannerException {
		Set<Structure> structures = scanner.getConfiguration().getStructures();
		for (Structure structure : structures) {

			Set<String> packageNames = structure.getPackages();
			for (String packageName : packageNames) {
				packageName = packageName.replace(".", File.separator);

				File testPackage = new File(scanner.getTest(), packageName);
				File[] testFiles = testPackage.listFiles();
				if (testFiles.length == 0) {
					throw new ScannerException(ErrorEnum.EMPTY_PACKAGE, packageName.replace(File.separator, "."));
				}

				for (File testFile : testFiles) {
					if(testFile.isDirectory()) {
						throw new ScannerException(ErrorEnum.INVALID_FILE, testFile.getName());
					}
					
					String filename = testFile.getName();
					boolean hasPrefix = filename.startsWith(structure.getType().getPrefix());
					boolean hasSuffix = filename.endsWith(SUFFIX);
					if (!hasPrefix || !hasSuffix) {
						throw new ScannerException(ErrorEnum.INVALID_FILENAME, filename);
					}

					File srcPackage = new File(scanner.getSrc(), packageName);
					filename = filename.replace(SUFFIX, EXTENSION);

					if (!hasFile(filename, srcPackage)) {
						throw new ScannerException(ErrorEnum.FILE_NOT_FOUND, filename);
					}
				}
			}
		}
	}

	private boolean hasFile(String filename, File directory) {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.getName().equals(filename)) {
				return true;
			}
		}

		return false;
	}

}
