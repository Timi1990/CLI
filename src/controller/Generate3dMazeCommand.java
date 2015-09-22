package controller;

import algorithms.mazeGenerators.Maze3d;
import model.Model;

import java.util.List;
import java.util.concurrent.*;

public class Generate3dMazeCommand implements Command
{
    private final Model model;

    Generate3dMazeCommand(Model model)
    {
        this.model = model;
    }

    @Override
    public void doCommand(List<String> args)
    {
        final String name = args.get(0);
        final Integer dimension = Integer.decode(args.get(1));
        final Integer rows = Integer.decode(args.get(2));
        final Integer columns = Integer.decode(args.get(3));

        Callable<String> callable = () ->
        {
            Maze3d maze3d = model.generateMaze3dBy(dimension, rows, columns);
            model.put(name, maze3d);

            return "Maze " + name + " is ready";
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Future<String> submit = executor.submit(callable);

        try
        {
            System.out.print(submit.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
