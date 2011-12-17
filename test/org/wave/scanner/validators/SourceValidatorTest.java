package org.wave.scanner.validators;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.validators.SourceValidator;


public class SourceValidatorTest {

	private SourceValidator validator;

	@Before
	public void setUp() {
		this.validator = new SourceValidator();
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOSourceFolderForNuloException() throws ScannerException {
		this.validator.validate(null);
	}

	@Test
	public void deveLancarExcecaoQuandoOSourceFolderForNulo() {
		try {
			this.validator.validate(null);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.NULL_FOLDER.getMessage(), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOSourceFolderNaoExistirException() throws ScannerException {
		File sourceFolder = new File("docs");

		this.validator.validate(sourceFolder);
	}

	@Test
	public void deveLancarExcecaoQuandoOSourceFolderNaoExistir() {
		File sourceFolder = new File("docs");

		try {
			this.validator.validate(sourceFolder);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.FOLDER_NOT_FOUND.getMessage(sourceFolder.getName()), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOSourceFolderNaoForDiretorioException() throws ScannerException {
		File sourceFolder = new File("test/org/wave/scanner/examples/src");

		this.validator.validate(sourceFolder);
	}

	@Test
	public void deveLancarExcecaoQuandoOSourceFolderNaoForDiretorio() {
		File sourceFolder = new File("test/org/wave/scanner/examples/src");

		try {
			this.validator.validate(sourceFolder);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_FOLDER.getMessage(sourceFolder.getName()), e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.validator = null;
	}

}
