package controller;

import java.util.List;

class FileSizeCommand implements Command
{
    @Override
    public void doCommand(List<String> args)
    {
        String filePath = args.get(0);

    }
}
