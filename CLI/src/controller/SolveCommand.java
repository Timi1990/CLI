package controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import model.Model;
import view.View;

/**
 * Solves a certain maze, when ready, sends message through the view
 *
 */
public class SolveCommand implements Command {
	
	private final Model model;
	private final View view;
	
	public SolveCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void doCommand(List<String> args) throws MazeDoesntExistsException {
		String name = args.get(0);
		String algorithm = args.get(1);
		Future<String> future=model.SolveMaze(name, algorithm);
		try {
			view.print(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
