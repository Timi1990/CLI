package controller;

import model.Model;
import view.View;

import java.util.HashMap;

public class MyController implements Controller
{
    private final Model model;
    private final View view;

    private final HashMap<String, Command> commandNameToCommand = new HashMap<String, Command>();

    public MyController(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public HashMap<String, Command> createCommandNameToCommand()
    {
        commandNameToCommand.put("Dir", new DirByPathCommand(view));
        commandNameToCommand.put("Generate", new Generate3dMazeCommand(model));
        commandNameToCommand.put("Display Maze", new DisplayCommand(model, view));
        commandNameToCommand.put("Display cross selection by", new DisplayCrossSectionCommand(model, view));
        commandNameToCommand.put("Save", new SaveCommand(model, view));
        commandNameToCommand.put("Load", new LoadCommand(model, view));
        commandNameToCommand.put("Maze Size", new MazeSizeCommand(model, view));
        commandNameToCommand.put("File Size", new FileSizeCommand());
        return commandNameToCommand;
    }
}