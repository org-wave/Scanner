package org.wave.scanner.receivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.wave.scanner.core.Reader;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.core.Writer;
import org.wave.scanner.elements.XHTML;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.factories.XHTMLFactory;
import org.wave.scanner.structures.Structure;


public class DocsFiller {

	private static final String XHTML_EXTENSION = ".xhtml";

	private static final String JAVA_EXTENSION = ".java";

	public static final String SUFFIX = "Test" + JAVA_EXTENSION;

	@Inject
	private Reader reader;

	@Inject
	private Writer writer;

	@SuppressWarnings("unused")
	private DocsFiller() {

	}

	public DocsFiller(Reader reader, Writer writer) {
		this.reader = reader;
		this.writer = writer;
	}

	public void fill(Scanner scanner) throws ScannerException {
		Set<Structure> structures = scanner.getConfiguration().getStructures();
		for (Structure structure : structures) {
			File folder = new File(scanner.getDocs(), structure.getType().getValue());
			if (!folder.mkdir()) {
				throw new ScannerException(ErrorEnum.FOLDER_NOT_CREATED, folder.getName());
			}

			Set<String> packages = structure.getPackages();
			for (String packageName : packages) {
				packageName = packageName.replace(".", File.separator);

				File testPackage = new File(scanner.getTest(), packageName);
				File[] testFiles = testPackage.listFiles();
				for (File testFile : testFiles) {
					List<Method> methods = this.reader.read(testFile);
					XHTML xhtml = XHTMLFactory.createXHTML(methods);

					File file = new File(folder, this.getFilename(testFile.getName(), structure));
					try {
						this.writer.write(xhtml, file);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						throw new ScannerException(e.getMessage());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						throw new ScannerException(e.getMessage());
					}
				}

			}
		}
	}

	private String getFilename(String classname, Structure structure) {
		classname = classname.replace(structure.getType().getPrefix(), "");
		classname = classname.replace(SUFFIX, XHTML_EXTENSION);
		classname = classname.toLowerCase();

		return classname;
	}

}
