package org.wave.scanner.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.examples.ClasseComMetodoPublicoAtTest;
import org.wave.utils.reflection.ReflectionUtil;

public class ReaderTest {

	private Reader reader;

	@Before
	public void setUp() {
		this.reader = new Reader();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoOArquivoDotJavaEstiverVazioException() {
		File file = new File("test/org/wave/scanner/examples/ArquivoVazio.java");

		this.reader.read(file);
	}

	@Test
	public void deveLancarExcecaoQuandoOArquivoDotJavaEstiverVazio() {
		File file = new File("test/org/wave/scanner/examples/ArquivoVazio.java");

		try {
			this.reader.read(file);
		} catch (Exception e) {
			assertEquals(ErrorEnum.EMPTY_FILE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveRetornarUmMetodoQuandoOArquivoDotJavaNaoTiverUmMetodoAnotadoComAtTest() {
		File file = new File(
				"test/org/wave/scanner/examples/ClasseComMetodoPublicoSemAtTest.java");

		List<Method> metodos = this.reader.read(file);

		assertTrue(metodos.isEmpty());
	}

	@Test
	public void naoDeveRetornarUmMetodoQuandoOArquivoDotJavaTiverUmMetodoAnotadoComAtTestQueEsperaUmaExcecao() {
		File file = new File(
				"test/org/wave/scanner/examples/ClasseComMetodoPublicoAtTestQueEsperaUmaExcecao.java");

		List<Method> metodos = this.reader.read(file);

		assertTrue(metodos.isEmpty());
	}

	@Test
	public void naoDeveRetornarUmMetodoQuandoOArquivoDotJavaTiverUmMetodoPrivadoEAnotadoComAtTest() {
		File file = new File(
				"test/org/wave/scanner/examples/ClasseComMetodoPrivadoAtTest.java");

		List<Method> metodos = this.reader.read(file);

		assertTrue(metodos.isEmpty());
	}

	@Test
	public void deveRetornarUmMetodoQuandoOArquivoDotJavaTiverUmMetodoPublicoAnotadoComAtTest() {
		File file = new File(
				"test/org/wave/scanner/examples/ClasseComMetodoPublicoAtTest.java");

		List<Method> metodos = this.reader.read(file);

		assertEquals(1, metodos.size());
		assertEquals(ReflectionUtil.getMethod("metodoComAtTest",
				ClasseComMetodoPublicoAtTest.class), metodos.get(0));
	}

	@After
	public void tearDown() {
		this.reader = null;
	}

}
