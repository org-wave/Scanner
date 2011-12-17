package org.wave.scanner.elements;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ul")
public class UL {

	@XStreamImplicit
	private List<String> lis;

	public UL() {
		this.lis = new ArrayList<String>();
	}

	public void add(String li) {
		this.lis.add(li);
	}
	
	public List<String> getLis() {
		return lis;
	}

}
