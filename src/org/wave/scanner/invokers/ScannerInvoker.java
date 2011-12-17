package org.wave.scanner.invokers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.wave.scanner.commands.CertifyConventionsCommand;
import org.wave.scanner.commands.Command;
import org.wave.scanner.commands.FillDocsCommand;
import org.wave.scanner.commands.RemovePackageCommand;
import org.wave.scanner.commands.SetConfigurationCommand;
import org.wave.scanner.commands.SetDocsCommand;
import org.wave.scanner.commands.SetSourcesCommand;


public abstract class ScannerInvoker {

	@Inject
	private SetSourcesCommand setSourcesCommand;

	@Inject
	private SetDocsCommand setDocsCommand;

	@Inject
	private SetConfigurationCommand setConfigurationCommand;

	@Inject
	private RemovePackageCommand removePackageCommand;

	@Inject
	private CertifyConventionsCommand certifyConventionsCommand;

	@Inject
	private FillDocsCommand fillDocsCommand;

	private List<Command> commands;

	public ScannerInvoker() {
		this.commands = new ArrayList<Command>();
	}

	@PostConstruct
	public void setCommands() {
		this.commands.add(this.setSourcesCommand);
		this.commands.add(this.setDocsCommand);
		this.commands.add(this.setConfigurationCommand);
		this.commands.add(this.removePackageCommand);
		this.commands.add(this.certifyConventionsCommand);
		this.commands.add(this.fillDocsCommand);
	}

	public List<Command> getCommands() {
		return commands;
	}

}
