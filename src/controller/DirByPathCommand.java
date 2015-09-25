package controller;

import view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays all files which located in given path
 *@see Command
 */
class DirByPathCommand implements Command
{
    private final View view;

    DirByPathCommand(View view)
    {
        this.view = view;
    }

    @Override
    public void doCommand(List<String> args)
    {
        String path = args.get(0);

        File file = new File(path);

        List<String> filesName = getChildBy(file);

        view.print(filesName);
    }

    private List<String> getChildBy(File file)
    {
        List<String> filesName = new ArrayList<>();

        for (File child : file.listFiles())
        {
            filesName.add(child.getName());
        }
        return filesName;
    }
}
