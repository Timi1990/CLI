package view;

import java.util.HashMap;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Command;

/**
 * Client side interface, displays different things according to command protocol
 *
 */
public interface View
{
    void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand);

    <T> void print(List<T> list);

    void print(Maze3d mazeName);

    void print(int[][] crossSelection);

    void print(Exception e);
    
     void print(long mazeSize);
     
     void print(String display);
     
     void print(Solution Solution);
     
   
    
    
}
