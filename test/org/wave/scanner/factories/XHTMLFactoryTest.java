package org.wave.scanner.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wave.scanner.elements.XHTML;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.factories.XHTMLFactory;
import org.wave.utils.reflection.ReflectionUtil;
import org.wave.utils.string.StringUtil;


public class XHTMLFactoryTest {
	
	public void setUp() {
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoAListaDeMetodosForNulaException() {
		XHTMLFactory.createXHTML(null);
	}

	@Test
	public void deveLancarExcecaoQuandoAListaDeMetodosForNula() {
		try {
			XHTMLFactory.createXHTML(null);
		} catch (Exception e) {
			assertEquals(ErrorEnum.NULL_LIST.getMessage(), e.getMessage());
		}
	}

	@Test
	public void deveRetornarUmXHTMLComULSemLIQuandoAListaDeMetodosEstiverVazia() {
		List<Method> methods = new ArrayList<Method>();
		XHTML xhtml = XHTMLFactory.createXHTML(methods);

		List<String> lis = xhtml.getBody().getUl().getLis();
		assertTrue(lis.isEmpty());
	}

	@Test
	public void deveRetornarUmXHTMLComUlELiQuandoAListaDeMetodosTiverUmElemento() {
		String methodName = "setUp";
		Method method = ReflectionUtil.getMethod(methodName, getClass());

		List<Method> methods = new ArrayList<Method>();
		methods.add(method);

		XHTML xhtml = XHTMLFactory.createXHTML(methods);

		List<String> lis = xhtml.getBody().getUl().getLis();
		assertEquals(1, lis.size());
		assertEquals(StringUtil.toHumanCase(methodName), lis.get(0));
	}

	@Test
	public void deveRetornarUmXHTMLComUlEMuitosLIQuandoAListaDeMetodosTiverMaisDeUmElemento() {
		String methodName01 = "setUp";
		Method method01 = ReflectionUtil.getMethod(methodName01, getClass());

		String methodName02 = "tearDown";
		Method method02 = ReflectionUtil.getMethod(methodName02, getClass());

		List<Method> methods = new ArrayList<Method>();
		methods.add(method01);
		methods.add(method02);

		XHTML xhtml = XHTMLFactory.createXHTML(methods);

		List<String> lis = xhtml.getBody().getUl().getLis();
		assertEquals(2, lis.size());
		assertEquals(StringUtil.toHumanCase(methodName01), lis.get(0));
		assertEquals(StringUtil.toHumanCase(methodName02), lis.get(1));
	}
	
	public void tearDown() {
		
	}

}
