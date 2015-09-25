package controller;

import java.util.List;

import view.View;

/**
 *Closes all streams and threads, and exits the program 
 *
 */
public class ExitCommand implements Command {
	
	private final View view;
	
	public ExitCommand(View view) {
		this.view=view;
	}
	@Override
	public void doCommand(List<String> args) {
		view.print("Exiting...");
		System.exit(1);
	}
	

}
