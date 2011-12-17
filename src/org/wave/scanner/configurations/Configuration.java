package org.wave.scanner.configurations;

import java.util.HashSet;
import java.util.Set;

import org.wave.scanner.enums.StructureEnum;
import org.wave.scanner.structures.Structure;


public class Configuration {

	private String domain;

	private String project;

	private Set<Structure> structures;

	public Configuration() {
		this.structures = new HashSet<Structure>();
	}

	public boolean hasDomain() {
		return this.domain != null && !this.domain.isEmpty();
	}

	public boolean hasProject() {
		return this.project != null && !this.project.isEmpty();
	}

	public Structure getStructure(StructureEnum type) {
		for (Structure structure : this.structures) {
			if (structure.getType().equals(type)) {
				return structure;
			}
		}

		return null;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProject() {
		return project.toLowerCase();
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Set<Structure> getStructures() {
		return structures;
	}

}
