import java.io.*;
import java.util.*;

//RobotPlanner is a driver class that takes in a grid environment from a text file and determines a path from a starting cell to a goal cell
//The A* algorithm (with the Manhattan heuristic) is used to find the path
public class RobotPlanner {
	//Drives the robot planner class
	//Reads a grid environment from a text file
	//Uses an AStar object to find the path
	public static void main(String[] args) {
		File inputEnvironment = new File(args[0]);
		int width = 0;
		int height = 0;
		Vector grid = new Vector();
		int startColumn = 0;
		int startRow = 0;
		int goalColumn = 0;
		int goalRow = 0;
		
		//Read in the grid from the input file
		//Store the grid in a vector of vectors
		//i.e.
		// {{0 0 1},
		//  {0 1 0},
		//  {0 1 1}}
		try {
			Scanner scanner = new Scanner(inputEnvironment);
			width = scanner.nextInt();
			height = scanner.nextInt();
			//Add each row of the grid
			for (int i = 0; i < height; i++) {
				grid.add(new Vector());
				//Add each column entry of the row
				for (int j = 0; j < width; j++) {
					((Vector)(grid.elementAt(i))).add(scanner.nextInt());
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		startColumn = Integer.parseInt(args[1]);
		startRow = Integer.parseInt(args[2]);
		goalColumn = Integer.parseInt(args[3]);
		goalRow = Integer.parseInt(args[4]);
		//Create an AStar object
		AStar aStar = new AStar(width, height, grid, startColumn, startRow, goalColumn, goalRow);
		//Find the path using the A* algorithm
		System.out.println(aStar.findPath());
	}
}