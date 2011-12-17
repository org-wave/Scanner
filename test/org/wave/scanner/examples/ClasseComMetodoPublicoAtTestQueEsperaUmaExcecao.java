package org.wave.scanner.examples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class ClasseComMetodoPublicoAtTestQueEsperaUmaExcecao {

	@Before
	public void setUp() {

	}

	@Test(expected = Exception.class)
	public void metodoComAtTest() {

	}

	@After
	public void tearDown() {

	}

	private void metodoPrivado() {

	}

}
