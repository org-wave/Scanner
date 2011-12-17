package org.wave.scanner.factories;

import java.lang.reflect.Method;
import java.util.List;

import org.wave.scanner.elements.BODY;
import org.wave.scanner.elements.UL;
import org.wave.scanner.elements.XHTML;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.utils.string.StringUtil;


public class XHTMLFactory {

	private XHTMLFactory() {

	}

	public static XHTML createXHTML(List<Method> methods) throws IllegalArgumentException {
		if (methods == null) {
			throw new IllegalArgumentException(ErrorEnum.NULL_LIST.getMessage());
		}

		UL ul = new UL();
		for (Method method : methods) {
			ul.add(StringUtil.toHumanCase(method.getName()));
		}

		BODY body = new BODY();
		body.setUl(ul);

		XHTML xhtml = new XHTML();
		xhtml.setBody(body);

		return xhtml;
	}

}
