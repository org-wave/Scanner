package org.wave.scanner.factories;

import java.io.File;

import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.structures.Structure;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

public class ConfigurationFactory {

	private ConfigurationFactory() {

	}

	public static Configuration create(File scannerXML) {
		XStream stream = new XStream(new PureJavaReflectionProvider());

		stream.useAttributeFor(Configuration.class, "domain");
		stream.useAttributeFor(Configuration.class, "project");
		stream.alias("scanner", Configuration.class);
		stream.addImplicitCollection(Configuration.class, "structures");

		stream.useAttributeFor(Structure.class, "type");
		stream.alias("structure", Structure.class);
		stream.addImplicitCollection(Structure.class, "packages");

		stream.alias("package", String.class);

		return (Configuration) stream.fromXML(scannerXML);
	}

}
