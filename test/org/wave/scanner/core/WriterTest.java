package org.wave.scanner.core;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.scanner.core.Writer;
import org.wave.scanner.elements.BODY;
import org.wave.scanner.elements.UL;
import org.wave.scanner.elements.XHTML;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.utils.file.FileUtil;

public class WriterTest {

	private File file;

	private Writer writer;

	@Before
	public void setUp() {
		this.file = new File("caminhoDoArquivo.xhtml");

		this.writer = new Writer();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoOXHTMLForNuloException() throws FileNotFoundException {
		this.writer.write(null, this.file);
	}

	@Test
	public void deveLancarExcecaoQuandoOXHTMLForNulo() throws FileNotFoundException {
		try {
			this.writer.write(null, this.file);
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorEnum.NULL_XHTML.getMessage(), e.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoOCaminhoDoArquivoForNuloException() throws FileNotFoundException {
		this.writer.write(new XHTML(), null);
	}

	@Test
	public void deveLancarExcecaoQuandoOCaminhoDoArquivoForNulo() throws FileNotFoundException {
		try {
			this.writer.write(new XHTML(), null);
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorEnum.NULL_FILE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void deveEscreverUmArquivoSemLIQuandoOXHTMLNaoTiverUmLI() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		builder.append("<xhtml>\n");
		builder.append("  <body>\n");
		builder.append("    <ul/>\n");
		builder.append("  </body>\n");
		builder.append("</xhtml>");

		UL ul = new UL();

		BODY body = new BODY();
		body.setUl(ul);

		XHTML xhtml = new XHTML();
		xhtml.setBody(body);

		this.writer.write(xhtml, this.file);

		File file = new File("caminhoDoArquivo.xhtml");
		String content = new Scanner(file).useDelimiter("\\Z").next();

		assertEquals(builder.toString(), content);

		FileUtil.delete(file);
	}

	@Test
	public void deveEscreverUmArquivoComLIQuandoOXHTMLTiverUmLI() throws FileNotFoundException {
		String value = "set up";

		StringBuilder builder = new StringBuilder();
		builder.append("<xhtml>\n");
		builder.append("  <body>\n");
		builder.append("    <ul>\n");
		builder.append("      <li>" + value + "</li>\n");
		builder.append("    </ul>\n");
		builder.append("  </body>\n");
		builder.append("</xhtml>");

		UL ul = new UL();
		ul.add(value);

		BODY body = new BODY();
		body.setUl(ul);

		XHTML xhtml = new XHTML();
		xhtml.setBody(body);

		this.writer.write(xhtml, this.file);

		File file = new File("caminhoDoArquivo.xhtml");
		String content = new Scanner(file).useDelimiter("\\Z").next();

		assertEquals(builder.toString(), content);

		FileUtil.delete(file);
	}

	@After
	public void tearDown() {
		this.writer = null;
	}

}
