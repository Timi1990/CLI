package view;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

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
    public <T> void print(T[][] crossSelection)
    {
        for (T[] aCrossSelection : crossSelection)
        {
            System.out.printf("{");
            for (T anACrossSelection : aCrossSelection)
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
}
