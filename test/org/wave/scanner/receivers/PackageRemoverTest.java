package org.wave.scanner.receivers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.InfoEnum;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.receivers.PackageRemover;
import org.wave.scanner.structures.Structure;


public class PackageRemoverTest {

	private String packageName;
	
	private Structure structure;

	private Scanner scanner;

	private Logger logger;

	private PackageRemover remover;

	@Before
	public void setUp() {
		this.logger = mock(Logger.class);
		
		this.packageName = "br.com.company.project.fluxos";

		this.structure = new Structure();
		this.structure.setType(StructureEnum.FLOW);
		this.structure.getPackages().add(this.packageName);

		Configuration configuration = new Configuration();
		configuration.getStructures().add(this.structure);

		this.scanner = new Scanner();
		this.scanner.setSrc(new File("srcWithPackage"));
		this.scanner.setTest(new File("testWithPackage"));
		this.scanner.setConfiguration(configuration);

		this.remover = new PackageRemover(this.logger);
	}

	@Test
	public void deveRemoverUmPacoteQuandoNaoExistirNoSRCFolderException() {
		this.scanner.setSrc(new File("srcWithoutPackage"));

		this.remover.removePackages(this.scanner);

		assertTrue(this.structure.isEmpty());

		verify(this.logger).info(InfoEnum.PACKAGE_NOT_FOUND.getMessage(this.packageName));
	}

	@Test
	public void deveRemoverUmPacoteQuandoNaoExistirNoTESTFolderException() {
		this.scanner.setTest(new File("testWithoutPackage"));

		this.remover.removePackages(this.scanner);

		assertTrue(this.structure.isEmpty());

		verify(this.logger).info(InfoEnum.PACKAGE_NOT_FOUND.getMessage(this.packageName));
	}

	@Test
	public void naoDeveRemoverUmPacoteQuandoExistirNoSRCFolderENoTESTFolder() {
		this.remover.removePackages(this.scanner);

		Set<String> packages = this.structure.getPackages();
		assertEquals(1, packages.size());
		
		for (String packageName : packages) {
			assertEquals(this.packageName, packageName);
		}
	}

	@After
	public void tearDown() {
		this.remover = null;
	}
	
}
