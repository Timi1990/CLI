package controller;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

import java.util.List;

class DisplayCommand implements Command
{
    private final Model model;
    private final View view;

    DisplayCommand(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args)
    {
        String name = args.get(0);

        Maze3d maze3d = model.getMazeBy(name);

        view.print(maze3d);
    }
}
