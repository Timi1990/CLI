package controller;

import model.Model;
import view.View;

import java.util.List;

class MazeSizeCommand implements Command
{
    private final Model model;
    private final View view;

    MazeSizeCommand(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args)
    {
        String name = args.get(0);

    }
}
