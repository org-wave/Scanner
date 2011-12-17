package org.wave.scanner.receivers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.Certifier;
import org.wave.scanner.structures.Structure;


public class CertifierTest {

	private Scanner scanner;

	public Certifier certifier;

	@Before
	public void setUp() {
		Structure structure = new Structure();
		structure.setType(StructureEnum.FLOW);
		structure.getPackages().add("br.com.company.project.fluxos");

		Configuration configuration = new Configuration();
		configuration.getStructures().add(structure);

		this.scanner = new Scanner();
		this.scanner.setSrc(new File("srcWithClass"));
		this.scanner.setTest(new File("testWithClass"));
		this.scanner.setConfiguration(configuration);

		this.certifier = new Certifier();
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOPacoteEstiverVazioException() throws ScannerException {
		this.scanner.setTest(new File("testWithPackage"));

		this.certifier.certify(this.scanner);
	}

	@Test
	public void deveLancarExcecaoQuandoOPacoteEstiverVazio() {
		this.scanner.setTest(new File("testWithPackage"));

		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.EMPTY_PACKAGE.getMessage("br.com.company.project.fluxos"), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOArquivoForUmDiretorioException() throws ScannerException {
		this.scanner.setTest(new File("testWithFolderAsClass"));

		this.certifier.certify(this.scanner);
	}

	@Test
	public void deveLancarExcecaoQuandoOArquivoForUmDiretorio() {
		this.scanner.setTest(new File("testWithFolderAsClass"));

		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_FILE.getMessage("FlowAcaoObjetoTest.java"), e.getMessage());
		}
	}
	

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoONomeDoArquivoNaoTiverOPrefixoConvencionadoException() throws ScannerException {
		this.scanner.setTest(new File("testWithoutConventionalPrefix"));

		this.certifier.certify(this.scanner);
	}

	@Test
	public void deveLancarExcecaoQuandoONomeDoArquivoNaoTiverOPrefixoConvencionado() {
		this.scanner.setTest(new File("testWithoutConventionalPrefix"));

		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_FILENAME.getMessage("ReportAcaoObjetoTest.java"), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoONomeDoArquivoNaoTiverOSufixoConvencionadoException() throws ScannerException {
		this.scanner.setTest(new File("testWithoutConventionalSuffix"));

		this.certifier.certify(this.scanner);
	}

	@Test
	public void deveLancarExcecaoQuandoONomeDoArquivoNaoTiverOSufixoConvencionado() {
		this.scanner.setTest(new File("testWithoutConventionalSuffix"));

		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_FILENAME.getMessage("ReportAcaoObjetoTest.java"), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOArquivoNaoExistirNoSRCFolderException() throws ScannerException {
		this.scanner.setSrc(new File("srcWithPackage"));
		this.scanner.setTest(new File("testWithClass"));

		this.certifier.certify(this.scanner);
	}

	@Test
	public void deveLancarExcecaoQuandoOArquivoNaoExistirNoSRCFolder() {
		this.scanner.setSrc(new File("srcWithPackage"));
		this.scanner.setTest(new File("testWithClass"));

		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.FILE_NOT_FOUND.getMessage("FlowAcaoObjeto.java"), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoOArquivoExistirNoSRCFolder() {
		try {
			this.certifier.certify(this.scanner);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.certifier = null;

		this.scanner = null;
	}

}
