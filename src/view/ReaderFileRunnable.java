package view;

import controller.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReaderFileRunnable implements Runnable
{
    private final BufferedReader in;
    private final PrintWriter out;
    private final HashMap<String, Command> commandHashMap;

    public ReaderFileRunnable(BufferedReader in, PrintWriter out, HashMap<String, Command> commandHashMap)
    {
        this.in = in;
        this.out = out;
        this.commandHashMap = commandHashMap;
    }

    @Override
    public void run()
    {
        String currentLine;

        try
        {
            while ((currentLine = in.readLine()) != null)
            {
                doCommandIfContains(currentLine);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void doCommandIfContains(final String command)
    {
        Boolean isContains = false;

        for (String commandsName : commandHashMap.keySet())
        {
            if(command.startsWith(commandsName))
            {
                List<String> args = convertToArrayBy(command);
                commandHashMap.get(commandsName).doCommand(args);

                isContains = true;
            }
        }

        if(isContains)
        {
            out.println("the command not found");
            out.close();
        }
    }

    private List<String> convertToArrayBy(String command)
    {
        String substring = substring(command);

        String[] split = substring.split("\\s+");
        return Arrays.asList(split);
    }

    private String substring(String command)
    {
        int start = command.indexOf('<');
        int end = command.lastIndexOf('>');
        return command.substring(start + 1, end);
    }





}
