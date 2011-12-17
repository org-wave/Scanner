package org.wave.scanner.receivers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.core.Scanner;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.receivers.ConfigurationSetter;
import org.wave.scanner.structures.Structure;
import org.wave.scanner.validators.ConfigurationValidator;

public class ConfigurationSetterTest {

	private static final String CONFIGURATION_FILE = File.separator + "META-INF" + File.separator + "scanner.xml";

	private File configurationFile;

	private Configuration configuration;

	private Scanner scanner;

	private ConfigurationSetter setter;

	@Before
	public void setUp() {
		this.configuration = new Configuration();

		this.scanner = new Scanner();
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOArquivoDeConfiguracaoNaoForEncontradoException() throws ScannerException {
		this.configurationFile = new File("srcEmpty" + CONFIGURATION_FILE);

		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		this.setter.validateConfigurationFile(this.configurationFile);
	}

	@Test
	public void deveLancarExcecaoQuandoOArquivoDeConfiguracaoNaoForEncontrado() {
		this.configurationFile = new File("srcEmpty" + CONFIGURATION_FILE);

		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		try {
			this.setter.validateConfigurationFile(this.configurationFile);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.CONFIGURATION_FILE_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoOArquivoDeConfiguracaoForUmDiretorioException() throws ScannerException {
		this.configurationFile = new File("srcFake" + CONFIGURATION_FILE);

		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		this.setter.validateConfigurationFile(this.configurationFile);
	}

	@Test
	public void deveLancarExcecaoQuandoOArquivoDeConfiguracaoForUmDiretorio() {
		this.configurationFile = new File("srcFake" + CONFIGURATION_FILE);

		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		try {
			this.setter.validateConfigurationFile(this.configurationFile);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION_FILE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void deveCarregarOsDadosDoArquivoDeConfiguracaoQuandoForValido() {
		this.configurationFile = new File("srcValid" + CONFIGURATION_FILE);

		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		try {
			Configuration configuration = this.setter.loadConfiguration(this.configurationFile);

			verify(validator).validate(configuration);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void naoDeveCarregarOsDadosDoArquivoDeConfiguracaoQuandoForInvalido() {
		this.configurationFile = new File("srcInvalid" + CONFIGURATION_FILE);

		ConfigurationValidator validator = new ConfigurationValidator();
		this.setter = new ConfigurationSetter(validator);

		try {
			this.setter.loadConfiguration(this.configurationFile);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveAdicionarPacotesPadraoQuandoNaoHouverDominio() {
		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		this.setter.setConfiguration(this.configuration, this.scanner);

		assertTrue(this.configuration.getStructures().isEmpty());
		assertEquals(this.configuration, this.scanner.getConfiguration());
	}

	@Test
	public void deveAdicionarPacotesPadraoQuandoHouverDominio() {
		ConfigurationValidator validator = mock(ConfigurationValidator.class);
		this.setter = new ConfigurationSetter(validator);

		this.configuration.setDomain("domain");
		this.setter.setConfiguration(this.configuration, this.scanner);

		Set<Structure> structures = this.configuration.getStructures();
		assertEquals(3, structures.size());

		for (StructureEnum type : StructureEnum.values()) {
			if (type.hasDefaultPackage()) {
				Structure structure = this.configuration.getStructure(type);

				Set<String> packages = structure.getPackages();
				assertEquals(1, packages.size());
				for (String packageName : packages) {
					assertEquals("domain." + type.getValue(), packageName);
				}
			}
		}

		assertEquals(this.configuration, this.scanner.getConfiguration());
	}

	@After
	public void tearDown() {
		this.setter = null;

		this.configuration = null;
	}

}
