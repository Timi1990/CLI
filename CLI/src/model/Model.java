package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.MazeDoesntExistsException;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * In charge of the data handling, operates different things in the data layer, and transfers relevant data to the
 * Controller
 * @see Controller
 */
public interface Model 
{
    void put(String mazeName, Maze3d maze3d);
    
    Maze3d getMazeBy(String mazeName) throws MazeDoesntExistsException;

    Future<String> generateMaze3dBy(String name,Integer dimension, Integer rows, Integer columns);
    
    int[][] getCrossSelectionBy(String zir, String mazeName, Integer index) throws IndexOutOfBoundsException, MazeDoesntExistsException;

    void save(String filePath, String mazeName) throws IOException, MazeDoesntExistsException;

    void load(String fileName, String mazeName) throws IOException, MazeDoesntExistsException;
    
    long calcSizeOfMazeInBytes(String name) throws MazeDoesntExistsException;
    
    Future<String> SolveMaze(String name,String algorithm) throws MazeDoesntExistsException;
    
    Solution getSolutionForMaze(String name) throws MazeDoesntExistsException;
    
   
}
