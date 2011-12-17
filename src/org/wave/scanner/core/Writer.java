package org.wave.scanner.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.wave.scanner.elements.XHTML;
import org.wave.scanner.enums.ErrorEnum;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

public class Writer {

	public void write(XHTML xhtml, File file) throws IllegalArgumentException, FileNotFoundException {
		if (xhtml == null) {
			throw new IllegalArgumentException(ErrorEnum.NULL_XHTML.getMessage());
		}

		if (file == null) {
			throw new IllegalArgumentException(ErrorEnum.NULL_FILE.getMessage());
		}

		XStream xStream = new XStream(new PureJavaReflectionProvider());
		xStream.autodetectAnnotations(true);
		xStream.alias("li", String.class);

		PrintStream printStream = new PrintStream(file);
		printStream.print(xStream.toXML(xhtml));

		printStream.close();
	}

}
