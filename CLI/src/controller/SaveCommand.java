package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.List;

/**
 * Saves certain maze by name given by client.
 *
 */
class SaveCommand implements Command
{
    private final Model model;
    private final View view;

    SaveCommand(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args) throws MazeDoesntExistsException
    {
        String mazeName = args.get(0);
        String filePath = args.get(1);

        try
        {
            model.save(filePath, mazeName);
        }
        catch (IOException e)
        {
            view.print(e);
        }
    }
}
