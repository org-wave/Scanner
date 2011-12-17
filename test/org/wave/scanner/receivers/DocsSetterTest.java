package org.wave.scanner.receivers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.DocsSetter;
import org.wave.utils.file.FileUtil;


public class DocsSetterTest {

	private File docs;

	private Scanner scanner;

	private DocsSetter setter;

	@Before
	public void setUp() {
		this.docs = new File("docs");
		assertFalse(this.docs.exists());

		this.scanner = new Scanner();

		this.setter = new DocsSetter();
		this.setter.setArguments(new String[] { "src", "test", "docs" });
	}

	@Test
	public void deveCriarUmaPastaDOCSQuandoNaoHouverAPasta() throws ScannerException {
		this.setter.setDocs(this.scanner);

		assertTrue(this.docs.exists());
		assertTrue(this.docs.isDirectory());
		assertTrue(this.docs.listFiles().length == 0);
	}

	@Test
	public void deveCriarUmaPastaDOCSQuandoJaHouverAPasta() throws ScannerException {
		assertTrue(this.docs.mkdir());
		assertTrue(this.docs.exists());
		assertTrue(this.docs.isDirectory());
		assertTrue(this.docs.listFiles().length == 0);

		this.setter.setDocs(this.scanner);

		assertTrue(this.docs.exists());
		assertTrue(this.docs.isDirectory());
		assertTrue(this.docs.listFiles().length == 0);
	}

	@After
	public void tearDown() {
		assertTrue(FileUtil.delete(this.docs));
		assertFalse(this.docs.exists());

		this.setter = null;
	}

}
