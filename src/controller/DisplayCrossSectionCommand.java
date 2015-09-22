package controller;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import model.Model;
import view.View;

import java.util.List;

class DisplayCrossSectionCommand implements Command
{
    private final Model model;
    private final View view;

    DisplayCrossSectionCommand(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args)
    {
        String zir = args.get(0);
        Integer index = Integer.decode(args.get(1));
        String name = args.get(2);

        try
        {
            Integer[][] crossSelection = model.getCrossSelectionBy(zir, name, index);
            view.print(crossSelection);
        }
        catch (IndexOutOfBoundsException e)
        {
            view.print(e);
        }
    }
}
