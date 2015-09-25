package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.MazeDoesntExistsException;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * A specific implementation of the Model interface, holds hashMaps of Mazes and their solutions
 * according to names, also in charge of:
 * -Saving and loading mazes to/from files.
 * -Generates and solves mazes.
 * -gets maze's cross selections.
 * -Calculates Maze's sizes.
 */
public class MyModel implements Model
{
	private final HashMap<String, Maze3d> mazeNameToMaze3d = new HashMap<String, Maze3d>();
	private final HashMap<Maze3d,Solution> mazeAndSolution=new HashMap<Maze3d,Solution>();
	private final Maze3dGenerator maze3dGenerator = new MyMaze3dGenerator();

	/* puts a maze into the hashMap bye name*/
	
	@Override
	public void put(String mazeName, Maze3d maze3d)
	{
		mazeNameToMaze3d.put(mazeName, maze3d);
	}
	/* gets a maze by name from the hashMap */
	
	@Override
	public Maze3d getMazeBy(String mazeName) throws MazeDoesntExistsException 
	{	
		if(mazeNameToMaze3d.containsKey(mazeName))
			return mazeNameToMaze3d.get(mazeName);
		else
			throw new MazeDoesntExistsException();
	}
	
	/* Generates maze in thread by clients parameters , sends a message when ready*/
	@Override
	public Future<String> generateMaze3dBy(String name,Integer dimension, Integer rows, Integer columns)
	{
		MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(dimension, rows, columns);
		ExecutorService executor=Executors.newFixedThreadPool(10);
		Future<String> future = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Maze3d maze = maze3dGenerator.generate(mazeArgumentsForInit);
				mazeNameToMaze3d.put(name, maze);
				return "Maze "+ name +" is ready";
			}
		});
		return future;
	}
	
	/* creates a cross selection of certain maze */
	@Override
	public int[][] getCrossSelectionBy(String axis, String mazeName, Integer index) throws IndexOutOfBoundsException, MazeDoesntExistsException
	{
		if(mazeNameToMaze3d.containsKey(mazeName))
		{
			Maze3d maze3d = getMazeBy(mazeName);

			return getCrossSelectionBy(axis, maze3d, index);
		}
		else
			throw new MazeDoesntExistsException();
	}

	private int[][] getCrossSelectionBy(String axis, Maze3d maze3d, Integer index) throws IndexOutOfBoundsException
	{
		switch (axis)
		{
		case "X":
		{
			return maze3d.getCrossSectionByX(index);
		}
		case "Y":
		{
			return maze3d.getCrossSectionByY(index);
		}
		case "Z":
		{
			return maze3d.getCrossSectionByZ(index);
		}
		}
		return null;
	}
	/* returns solution of a maze by name (if exists)*/
	public Solution getSolutionForMaze(String name) throws MazeDoesntExistsException
	{
		if(mazeNameToMaze3d.containsKey(name))
		{
			Maze3d maze=mazeNameToMaze3d.get(name);
			return mazeAndSolution.get(maze);
		}
		else
			throw new MazeDoesntExistsException();
	}
	
	/* Saves a maze to current path */
	@Override
	public void save(String filePath, String mazeName) throws IOException,MazeDoesntExistsException
	{
		if(mazeNameToMaze3d.containsKey(mazeName))
		{
			Maze3d maze3d = mazeNameToMaze3d.get(mazeName);

			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(filePath));
			out.write(maze3d.toByteArray());
			out.close();
		}
		else
			throw new MazeDoesntExistsException();
	}
	
	/* Loads a maze from current path */
	@Override
	public void load(String fileName, String mazeName) throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream(fileName);
		InputStream in = new MyDecompressorInputStream(fileInputStream);

		byte b[] = new byte[5000];
		in.read(b);
		in.close();

		Maze3d maze3d = new Maze3d(b);
		put(mazeName, maze3d);
	}
	
	/* Calculates maze's size in bytes */
	@Override
	public long calcSizeOfMazeInBytes(String name) throws  MazeDoesntExistsException
	{
		if(mazeNameToMaze3d.containsKey(name))
		{
			Maze3d maze=mazeNameToMaze3d.get(name);
			long length = maze.toByteArray().length;
			return length;
		}
		else
			throw new MazeDoesntExistsException();
	}
	
	/* Solves a maze with BFS/Astar algorithm in thread, sends message when ready*/ 
	@Override
	public Future<String> SolveMaze(String name, String algorithm) throws  MazeDoesntExistsException
	{
		if(mazeNameToMaze3d.containsKey(name))
		{
		HashMap<String,Searcher> algorithmFactory=new HashMap<String,Searcher>();
		algorithmFactory.put("BFS", new BFS());
		algorithmFactory.put("AStar", new Astar(new MazeManhattanDistance()));
		if(algorithmFactory.containsKey(algorithm)){
			Maze3d maze=mazeNameToMaze3d.get(name);
			Searchable searchable=new Maze3dDomain(maze);
			ExecutorService executor=Executors.newFixedThreadPool(10);
			Future<String> future=executor.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					Solution solution=algorithmFactory.get(algorithm).search(searchable);
					mazeAndSolution.put(maze, solution);
					return "Solution for " +name+ " is ready";
				}
			});
			return future;
		}
		else{
			System.err.println("algorithm doesn't exists...");	}	
		}
		else
			throw new MazeDoesntExistsException();
		return null;
	}
}