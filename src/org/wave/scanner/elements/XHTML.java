package org.wave.scanner.elements;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xhtml")
public class XHTML {
	
	private HEAD head;
	
	private BODY body;

	public HEAD getHead() {
		return head;
	}

	public void setHead(HEAD head) {
		this.head = head;
	}

	public BODY getBody() {
		return body;
	}

	public void setBody(BODY body) {
		this.body = body;
	}

}
