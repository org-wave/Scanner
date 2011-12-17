package org.wave.scanner.elements;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class BODY {
	
	private UL ul;

	public UL getUl() {
		return ul;
	}

	public void setUl(UL ul) {
		this.ul = ul;
	}

}
