package org.wave.test.core;
//package br.com.brasilti.test.core;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.io.File;
//
//import org.jboss.weld.environment.se.Weld;
//import org.jboss.weld.environment.se.WeldContainer;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import br.com.brasilti.scanner.configurations.Configuration;
//import br.com.brasilti.scanner.core.Scanner;
//import br.com.brasilti.scanner.enums.FileEnum;
//import br.com.brasilti.scanner.exceptions.ScannerException;
//import br.com.brasilti.scanner.factories.ConfigurationFactory;
//import br.com.brasilti.utils.file.FileUtil;
//
//public class ScannerTest {
//
//	private Configuration configuration;
//
//	private File docs;
//
//	private Scanner scanner;
//
//	@Before
//	public void setUp() {
//		this.configuration = ConfigurationFactory.create();
//
//		this.docs = new File(FileEnum.DOCS_FOLDER.getValue());
//		assertFalse(this.docs.exists());
//
//		WeldContainer container = new Weld().initialize();
//		this.scanner = container.instance().select(Scanner.class).get();
//
//		this.scanner.setConfiguration(this.configuration);
//
//		File test = new File(FileEnum.TEST_FOLDER.getValue());
//		this.scanner.setTest(test);
//	}
//
//	@Test
//	public void deveCriarUmaPastaDOCSVaziaNaRaizDaAplicacaoQuandoNaoHouverDocumentacao() throws ScannerException {
//		this.scanner.setTest(null);
//
//		this.scanner.scan();
//
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//		assertEquals(0, this.docs.listFiles().length);
//	}
//
//	@Test
//	public void deveCriarUmaPastaDOCSVaziaNaRaizDaAplicacaoQuandoHouverUmaDocumentacaoAnterior() throws ScannerException {
//		assertTrue(this.docs.mkdir());
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//
//		this.scanner.setTest(null);
//
//		this.scanner.scan();
//
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//		assertEquals(0, this.docs.listFiles().length);
//	}
//
//	@Test
//	public void naoDevePreencherAPastaDOCSQuandoNaoHouverPacotesPadrao() throws ScannerException {
//		this.configuration = ConfigurationFactory.create("src/META-INF/scannerNoFlows.xml");
//		this.scanner.setConfiguration(this.configuration);
//
//		this.scanner.scan();
//
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//		assertEquals(0, this.docs.listFiles().length);
//	}
//
//	@Test
//	public void naoDevePreencherAPastaDOCSQuandoOPacotePadraoEstiverVazio() throws ScannerException {
//		this.configuration = ConfigurationFactory.create("src/META-INF/scannerNoClass.xml");
//		this.scanner.setConfiguration(this.configuration);
//
//		this.scanner.scan();
//
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//		assertEquals(0, this.docs.listFiles().length);
//	}
//
//	@Test
//	public void devePreencherAPastaDOCSQuandoHouverClassesEmPacotesPadrao() throws ScannerException {
//		this.scanner.scan();
//
//		assertTrue(this.docs.exists());
//		assertTrue(this.docs.isDirectory());
//
//		File[] directories = this.docs.listFiles();
//		assertEquals(2, directories.length);
//
//		File flowsFolder = directories[0];
//		assertEquals("flows", flowsFolder.getName());
//
//		File[] flows = flowsFolder.listFiles();
//		assertEquals(1, flows.length);
//		for (File flow : flows) {
//			assertEquals("acaoobjeto.xhtml", flow.getName());
//		}
//
//		File reportsFolder = directories[1];
//		assertEquals("reports", reportsFolder.getName());
//
//		File[] reports = reportsFolder.listFiles();
//		assertEquals(1, reports.length);
//		for (File report : reports) {
//			assertEquals("objetoporcriterio.xhtml", report.getName());
//		}
//	}
//
//	@After
//	public void tearDown() {
//		assertTrue(FileUtil.delete(this.docs));
//		assertFalse(this.docs.exists());
//	}
//
// }
