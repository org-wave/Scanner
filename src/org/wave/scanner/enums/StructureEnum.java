package org.wave.scanner.enums;

import org.wave.scanner.configurations.Configuration;
import org.wave.scanner.structures.Structure;


public enum StructureEnum {

	FLOW("flows", "Flow"),
	REPORT("reports", "Report"),
	TASK("tasks", "Task"),
	OTHER("others", "") {
		@Override
		public boolean hasDefaultPackage() {
			return false;
		}
	};

	private String value;

	private String prefix;

	private StructureEnum(String value, String prefix) {
		this.value = value;
		this.prefix = prefix;
	}

	public void addDefaultPackage(Configuration configuration) {
		if (this.hasDefaultPackage()) {
			Structure structure = configuration.getStructure(this);
			if (structure == null) {
				structure = new Structure();
				structure.setType(this);
				configuration.getStructures().add(structure);
			}

			String packageName = this.getPackageName(configuration);
			structure.getPackages().add(packageName);
		}
	}

	private String getPackageName(Configuration configuration) {
		StringBuilder builder = new StringBuilder();
		builder.append(configuration.getDomain());
		builder.append(".");

		if (configuration.hasProject()) {
			builder.append(configuration.getProject());
			builder.append(".");
		}

		builder.append(this.value);

		return builder.toString();
	}

	public boolean hasDefaultPackage() {
		return true;
	}

	public String getValue() {
		return value;
	}

	public String getPrefix() {
		return prefix;
	}

}
