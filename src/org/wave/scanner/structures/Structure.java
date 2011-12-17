package org.wave.scanner.structures;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.wave.scanner.enums.StructureEnum;


public class Structure {

	private StructureEnum type;

	private Set<String> packages;

	public Structure() {
		this.type = StructureEnum.OTHER;

		this.packages = new HashSet<String>();
	}

	public boolean isEmpty() {
		return this.getPackages().isEmpty();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Structure structure = (Structure) obj;
		return new EqualsBuilder().append(this.type, structure.type).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.type).toHashCode();
	}

	public StructureEnum getType() {
		return type;
	}

	public void setType(StructureEnum type) {
		this.type = type;
	}

	public Set<String> getPackages() {
		return packages;
	}

}
