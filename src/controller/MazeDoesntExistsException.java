package controller;

/**
 * Exception for wrong maze name entered by client
 *
 */
public class MazeDoesntExistsException extends Exception {
	
	public MazeDoesntExistsException() {
		System.out.println("Maze doesn't exists");
		super.printStackTrace();
	}
}
