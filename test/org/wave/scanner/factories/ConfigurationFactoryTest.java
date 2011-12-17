package org.wave.scanner.factories;

import static junit.framework.Assert.assertEquals;

import java.io.File;
import java.util.Set;

import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.factories.ConfigurationFactory;
import org.wave.scanner.structures.Structure;

public class ConfigurationFactoryTest {

	@Test
	public void deveRetornarOsDadosDoArquivoDeConfiguracaoQuandoOXMLEstiverPreenchido() {
		File scannerXML = new File("test/org/wave/scanner/factories/scanner.xml");
		Configuration configuration = ConfigurationFactory.create(scannerXML);

		assertEquals("br.com.company", configuration.getDomain());
		assertEquals("project", configuration.getProject());

		Set<Structure> structures = configuration.getStructures();
		assertEquals(4, structures.size());

		for (Structure structure : structures) {
			assertEquals(2, structure.getPackages().size());
		}
	}

}
