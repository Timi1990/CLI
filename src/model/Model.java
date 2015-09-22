package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;

import java.io.IOException;

public interface Model
{
    void put(String mazeName, Maze3d maze3d);
    Maze3d getMazeBy(String mazeName);

    Maze3d generateMaze3dBy(Integer dimension, Integer rows, Integer columns);
    Integer[][] getCrossSelectionBy(String zir, String mazeName, Integer index) throws IndexOutOfBoundsException;

    void save(String filePath, String mazeName) throws IOException;

    void load(String fileName, String mazeName) throws IOException;

    Integer calcSizeOf(Maze3d maze3d);
}
