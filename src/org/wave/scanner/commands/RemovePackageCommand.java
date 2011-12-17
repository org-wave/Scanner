package org.wave.scanner.commands;

import javax.inject.Inject;

import org.wave.scanner.core.Scanner;
import org.wave.scanner.receivers.PackageRemover;


public class RemovePackageCommand implements Command {

	@Inject
	private PackageRemover remover;

	@SuppressWarnings("unused")
	private RemovePackageCommand() {

	}

	public RemovePackageCommand(PackageRemover remover) {
		this.remover = remover;
	}

	@Override
	public void execute(Scanner scanner) {
		this.remover.removePackages(scanner);
	}

}
