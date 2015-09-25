package controller;

import java.util.List;

/**
 * A Command interface which indicates how to handle with different commands
 * according to client requests.
 * All Commands communicate with the Model interface
 * @see Model
 */
public interface Command
{
    void doCommand(List<String> args) throws MazeDoesntExistsException;
}
