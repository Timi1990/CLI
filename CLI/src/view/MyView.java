package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Command;

/**
 * A specific implementation of View, activates CLI's thread.
 *@see View
 */
public class MyView implements View
{
    private final CLIFactory cliFactory;

    public MyView()
    {
        cliFactory = new CLIFactory();
    }

    @Override
    public void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(fileInput));
            PrintWriter out = new PrintWriter(fileOutput);

            CLI cli = cliFactory.createFrom(in, out, stringToCommand);
            cli.start();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void print(List<T> list)
    {
        list.forEach(System.out::println);
    }

    @Override
    public void print(Maze3d maze3d)
    {
        maze3d.printMaze();
    }

    @Override
    public void print(int[][] crossSelection)
    {
        for (int[] aCrossSelection : crossSelection)
        {
            System.out.printf("{");
            for (int anACrossSelection : aCrossSelection)
            {
                System.out.printf(anACrossSelection + ",");
            }
            System.out.printf("}");
            System.out.printf("\n");
        }
    }

    @Override
    public void print(Exception e)
    {
        System.out.print(e);
    }

	

	@Override
	public void print(Solution solution) {
		solution.printSolution();
		
	}

	@Override
	public void print(long mazeSize) {
		System.out.println("Maze size in memory is "+ mazeSize+" bytes");
		
	}

	@Override
	public void print(String display) {
		System.out.println(display);
	}
}
