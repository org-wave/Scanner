package org.wave.scanner.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.structures.Structure;


public class StructureEnumTest {

	private Configuration configuration;

	private StructureEnum type;

	@Before
	public void setUp() {
		this.configuration = new Configuration();
		
		this.type = StructureEnum.FLOW;
	}

	@Test
	public void naoDeveAdicionarONomeDoPacoteQuandoOTipoNaoTiverPacotePadrao() {
		this.type = StructureEnum.OTHER;
		assertFalse(this.type.hasDefaultPackage());
		
		Structure structure = new Structure();
		structure.setType(this.type);
		this.configuration.getStructures().add(structure);
		
		this.type.addDefaultPackage(this.configuration);

		Set<String> packages = this.configuration.getStructure(this.type).getPackages();
		assertTrue(packages.isEmpty());
	}

	@Test
	public void deveAdicionarONomeDoPacoteSemProjetoQuandoAConfiguracaoNaoTiverProjeto() {
		this.configuration.setDomain("domain");

		this.type.addDefaultPackage(this.configuration);

		Structure structure = this.configuration.getStructure(this.type);
		Set<String> packages = structure.getPackages();
		assertEquals(1, packages.size());

		for (String packageName : packages) {
			assertEquals("domain.flows", packageName);
		}
	}

	@Test
	public void deveAdicionarONomeDoPacoteComProjetoQuandoAConfiguracaoTiverProjeto() {
		this.configuration.setDomain("domain");
		this.configuration.setProject("project");

		this.type.addDefaultPackage(this.configuration);

		Structure structure = this.configuration.getStructure(this.type);
		Set<String> packages = structure.getPackages();
		assertEquals(1, packages.size());

		for (String packageName : packages) {
			assertEquals("domain.project.flows", packageName);
		}
	}
	
	@After
	public void tearDown() {
		this.type = null;
		
		this.configuration = null;
	}

}
