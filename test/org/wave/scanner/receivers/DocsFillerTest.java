package org.wave.scanner.receivers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Reader;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.core.Writer;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.DocsFiller;
import org.wave.scanner.structures.Structure;
import org.wave.utils.file.FileUtil;

//TODO Implementar testes.
public class DocsFillerTest {
	
	private File docs;

	private Scanner scanner;

	private Reader reader;

	private Writer writer;

	private DocsFiller filler;

	@Before
	public void setUp() {
		this.docs = new File("docsWithClass");
		assertTrue(this.docs.mkdir());
		
		this.reader = mock(Reader.class);
		this.writer = mock(Writer.class);
		
		this.filler = new DocsFiller(this.reader, this.writer);
		
		Structure structure = new Structure();
		structure.setType(StructureEnum.FLOW);
		structure.getPackages().add("br.com.company.project.fluxos");

		Configuration configuration = new Configuration();
		configuration.getStructures().add(structure);

		this.scanner = new Scanner();
		this.scanner.setSrc(new File("srcWithClass"));
		this.scanner.setTest(new File("testWithClass"));
		this.scanner.setDocs(this.docs);
		this.scanner.setConfiguration(configuration);
	}

	@Test
	public void devePreencherAPastaDOCSQuandoNaoHouverClassesEmPacotes() {
		try {
			this.filler.fill(this.scanner);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		assertTrue(FileUtil.delete(this.docs));
		
		this.filler = null;
	}

}
