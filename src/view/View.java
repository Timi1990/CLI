package view;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

import java.util.HashMap;
import java.util.List;

public interface View
{
    void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand);

    <T> void print(List<T> list);

    void print(Maze3d mazeName);

    <T>void print(T[][] crossSelection);

    void print(Exception e);
}
