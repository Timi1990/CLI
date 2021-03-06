package view;

import controller.Command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Creates several CLI's from different variables
 *
 */
public class CLIFactory
{
    public CLI createFrom(BufferedReader in, PrintWriter out, HashMap<String, Command> commandHashMap)
    {
        ReaderFileRunnable readerFileRunnable = new ReaderFileRunnable(in, out, commandHashMap);

        return new CLI(readerFileRunnable);
    }
}
