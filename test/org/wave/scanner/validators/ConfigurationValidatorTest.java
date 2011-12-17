package org.wave.scanner.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.exceptions.ScannerException;
import org.wave.scanner.structures.Structure;
import org.wave.scanner.validators.ConfigurationValidator;


public class ConfigurationValidatorTest {

	private Configuration configuration;

	private ConfigurationValidator validator;

	@Before
	public void setUp() {
		String packageName = "br.com.company.project.core";

		Structure structure = new Structure();
		structure.setType(StructureEnum.FLOW);
		structure.getPackages().add(packageName);

		this.configuration = new Configuration();
		this.configuration.setDomain("br.com.company");
		this.configuration.setProject("project");
		this.configuration.getStructures().add(structure);

		this.validator = new ConfigurationValidator();
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoODominioForNuloEAsEstruturasEstiveremVaziasException() throws ScannerException {
		this.configuration.setDomain(null);
		this.configuration.getStructures().clear();

		this.validator.validate(this.configuration);
	}

	@Test
	public void deveLancarExcecaoQuandoODominioForNuloEAsEstruturasEstiveremVazias() {
		this.configuration.setDomain(null);
		this.configuration.getStructures().clear();

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION.getMessage(), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoODominioForVazioEAsEstruturasEstiveremVaziasException() throws ScannerException {
		this.configuration.setDomain("");
		this.configuration.getStructures().clear();

		this.validator.validate(this.configuration);
	}

	@Test
	public void deveLancarExcecaoQuandoODominioForVazioEAsEstruturasEstiveremVazias() {
		this.configuration.setDomain("");
		this.configuration.getStructures().clear();

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION.getMessage(), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoODominioForNuloEOsPacotesEstiveremVaziosException() throws ScannerException {
		this.configuration.setDomain(null);
		this.configuration.getStructure(StructureEnum.FLOW).getPackages().clear();

		this.validator.validate(this.configuration);
	}

	@Test
	public void deveLancarExcecaoQuandoODominioForNuloEOsPacotesEstiveremVazios() {
		this.configuration.setDomain(null);
		this.configuration.getStructure(StructureEnum.FLOW).getPackages().clear();

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION.getMessage(), e.getMessage());
		}
	}

	@Test(expected = ScannerException.class)
	public void deveLancarExcecaoQuandoODominioForVazioEOsPacotesEstiveremVaziosException() throws ScannerException {
		this.configuration.setDomain("");
		this.configuration.getStructure(StructureEnum.FLOW).getPackages().clear();

		this.validator.validate(this.configuration);
	}

	@Test
	public void deveLancarExcecaoQuandoODominioForVazioEOsPacotesEstiveremVazios() {
		this.configuration.setDomain("");
		this.configuration.getStructure(StructureEnum.FLOW).getPackages().clear();

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			assertEquals(ErrorEnum.INVALID_CONFIGURATION.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoODominioForNuloEAsEstruturasEPacotesEstiveremPreenchidos() {
		this.configuration.setDomain(null);

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoODominioForVazioEAsEstruturasEPacotesEstiveremPreenchidos() {
		this.configuration.setDomain("");

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoODominioEstiverPreenchidoEAsEstruturasEstiveremVazias() {
		this.configuration.getStructures().clear();

		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoODominioEstiverPreenchidoEAsEstruturasEstiveremPreenchidas() {
		try {
			this.validator.validate(this.configuration);
		} catch (ScannerException e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.validator = null;
		this.configuration = null;
	}

}
