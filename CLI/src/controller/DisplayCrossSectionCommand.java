package controller;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import model.Model;
import view.View;

import java.util.List;

/**
 * Displays through the view cross section of a maze by parameters which were sent by the client
 *
 */
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
    public void doCommand(List<String> args) throws MazeDoesntExistsException
    {
        String axis = args.get(0);
        Integer index = Integer.decode(args.get(1));
        String name = args.get(2);

        try
        {
            int[][] crossSelection = model.getCrossSelectionBy(axis, name, index);
            view.print(crossSelection);
        }
        catch (IndexOutOfBoundsException e)
        {
            view.print(e);
        }
    }
}
