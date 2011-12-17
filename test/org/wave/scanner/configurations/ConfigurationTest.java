package org.wave.scanner.configurations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.structures.Structure;


public class ConfigurationTest {

	private Configuration configuration;

	@Before
	public void setUp() {
		this.configuration = new Configuration();
	}

	@Test
	public void deveRetornarVerdadeiroQuandoHouverDominio() {
		this.configuration.setDomain("domain");

		assertTrue(this.configuration.hasDomain());
	}

	@Test
	public void deveRetornarFalsoQuandoDominioForNulo() {
		this.configuration.setDomain(null);

		assertFalse(this.configuration.hasDomain());
	}

	@Test
	public void deveRetornarFalsoQuandoDominioForVazio() {
		this.configuration.setDomain("");

		assertFalse(this.configuration.hasDomain());
	}

	@Test
	public void deveRetornarVerdadeiroQuandoHouverProjeto() {
		this.configuration.setProject("project");

		assertTrue(this.configuration.hasProject());
	}

	@Test
	public void deveRetornarFalsoQuandoProjetoForNulo() {
		this.configuration.setProject(null);

		assertFalse(this.configuration.hasProject());
	}

	@Test
	public void deveRetornarFalsoQuandoProjetoForVazio() {
		this.configuration.setProject("");

		assertFalse(this.configuration.hasProject());
	}

	@Test
	public void deveRetornarNuloQuandoOTipoDaEstruturaForNulo() {
		assertNull(this.configuration.getStructure(null));
	}

	@Test
	public void deveRetornarNuloQuandoNaoHouverAEstruturaDeUmDeterminadoTipo() {
		Structure structure = new Structure();
		structure.setType(StructureEnum.FLOW);
		this.configuration.getStructures().add(structure);

		assertNull(this.configuration.getStructure(StructureEnum.REPORT));
	}

	@Test
	public void deveRetornarAEstruturaQuandoHouverAEstruturaDeUmDeterminadoTipo() {
		Structure structure = new Structure();
		structure.setType(StructureEnum.FLOW);
		this.configuration.getStructures().add(structure);

		assertEquals(structure, this.configuration.getStructure(StructureEnum.FLOW));
	}

	@After
	public void tearDown() {
		this.configuration = null;
	}

}
