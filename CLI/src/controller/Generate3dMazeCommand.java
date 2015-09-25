package controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import model.Model;
import view.View;

/**
 * Generates a maze by parameters were given by client in the model interface,
 * sends a message when ready to the view.
 *
 */
public class Generate3dMazeCommand implements Command
{
    private final Model model;
    private final View view;

    Generate3dMazeCommand(Model model,View view)
    {
        this.model = model;
        this.view=view;
    }

    @Override
    public void doCommand(List<String> args)
    {
        final String name = args.get(0);
        final Integer dimension = Integer.decode(args.get(1));
        final Integer rows = Integer.decode(args.get(2));
        final Integer columns = Integer.decode(args.get(3));
        Future <String> future = model.generateMaze3dBy(name, dimension, rows, columns);
        try {
			view.print(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    }
}
