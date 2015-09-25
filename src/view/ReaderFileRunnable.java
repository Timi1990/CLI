package view;

import controller.Command;
import controller.MazeDoesntExistsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Read and write to files class, implements Runnable, runs as thread
 *
 */
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

	/**
	 * Reads from input stream, and matches requests to various commands, according
	 * to given command protocol.
	 * Also in charge of closing the streams and runs as thread.
	 */
	
	@Override
	public void run()
	{
		String currentLine;

		try
		{
			while ((currentLine = in.readLine()) != null)
			{
				if(currentLine.equals("Exit"))
				{
					commandHashMap.get(currentLine).doCommand(null);
				}
				doCommandIfContains(currentLine);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
		}
	}
	
	/**
	 * Checks if the command exists in the commands hashMap, if so, converts it to
	 * List and sends to the controller to operate it
	 * @param command
	 * @throws MazeDoesntExistsException
	 */
	private void doCommandIfContains(final String command) throws MazeDoesntExistsException
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
