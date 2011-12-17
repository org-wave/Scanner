package org.wave.scanner.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.structures.Structure;

public class StructureTest {

	private Structure structure;

	@Before
	public void setUp() {
		this.structure = new Structure();
		this.structure.getPackages().add("br.com.brasilti.scanner.examples.flows");
	}

	@Test
	public void deveRetornarFalsoQuandoHouverPacotes() {
		assertFalse(this.structure.isEmpty());
		assertEquals(this.structure.getPackages().isEmpty(), this.structure.isEmpty());
	}

	@Test
	public void deveRetornarVerdadeiroQuandoNaoHouverPacotes() {
		this.structure.getPackages().clear();

		assertTrue(this.structure.isEmpty());
		assertEquals(this.structure.getPackages().isEmpty(), this.structure.isEmpty());
	}

	@After
	public void tearDown() {
		this.structure = null;
	}

}
