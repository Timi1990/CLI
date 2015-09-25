package controller;

import java.util.List;

import model.Model;
import view.View;

/**
 * Displays through the view the file size of a certain maze
 *
 */
class FileSizeCommand implements Command
{
	private final Model model;
	private final View view;
	public FileSizeCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}
    @Override
    public void doCommand(List<String> args) throws MazeDoesntExistsException
    {
    	String name = args.get(0);
    	view.print((long)model.calcSizeOfMazeInBytes(name));
    }
}
