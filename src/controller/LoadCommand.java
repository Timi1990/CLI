package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.List;

/**
 * Loads a maze by name from certain path given by client.
 *
 */
class LoadCommand implements Command
{
    private final Model model;
    private final View view;

    LoadCommand(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args) throws MazeDoesntExistsException
    {
        String fileName = args.get(0);
        String mazeName = args.get(1);

        try
        {
            model.load(fileName, mazeName);

        } catch (IOException e)
        {
            view.print(e);
        }
    }
}
