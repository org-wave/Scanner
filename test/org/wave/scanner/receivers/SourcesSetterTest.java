package org.wave.scanner.receivers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.SourcesSetter;
import org.wave.scanner.validators.SourceValidator;


public class SourcesSetterTest {

	private Scanner scanner;

	private SourcesSetter setter;

	@Before
	public void setUp() {
		this.scanner = new Scanner();
	}

	@Test
	public void deveAtribuirSourcesQuandoForemValidos() {
		SourceValidator validator = mock(SourceValidator.class);
		this.setter = new SourcesSetter(validator);
		this.setter.setArguments(new String[] { "src", "test", "docs" });

		try {
			this.setter.setSources(this.scanner);

			verify(validator).validate(this.scanner.getSrc());
			verify(validator).validate(this.scanner.getTest());
		} catch (ScannerException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void naoDeveAtribuirSourcesQuandoForemInvalidos() {
		String argumento = "srcFake";
		SourceValidator validator = new SourceValidator();
		this.setter = new SourcesSetter(validator);
		this.setter.setArguments(new String[] { argumento, "test", "docs" });

		try {
			this.setter.setSources(this.scanner);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.FOLDER_NOT_FOUND.getMessage(argumento), e.getMessage());
		}

	}

	@After
	public void tearDown() {
		this.scanner = null;

		this.setter = null;
	}

}
