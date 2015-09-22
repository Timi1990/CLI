package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.List;

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
    public void doCommand(List<String> args)
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
