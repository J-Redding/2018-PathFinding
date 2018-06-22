import java.util.*;

//This class represents a cell as it is visited and put into the fringe of the A* search algorithm
//This class can also be thought of as a node that has been expanded
//It's important to note that each cell object represents a cell visited in a particular path
//Therefore the same grid position may be represented by multiple cell objects if the order in which they arrived at that particular position differs
public class Cell implements Comparator<Cell> {
	//Cells have a position in the grid; a row and a column
	int column;
	int row;
	//Cost to reach this cell from the starting position
	int stepCost;
	//The calculated cost of following this path to get to the goal
	//Path cost is step cost + heuristic
	int pathCost;
	//pathHistory is a vector of strings that represent the history of cells visited along this cell's path
	//This lets us check to see if we have visited a cell before in our path before visiting it again
	//This prevents infinite loops of hopping between two cells
	//Visited cells are represented in the string format "[column],[row]"
	Vector pathHistory;
	//A string representing the actions taken to arrive at the current cell
	//Actions are space-seperated, and can be U, R, D, or L
	String actionHistory;

	public Cell() {

	}

	public Cell(int column_in, int row_in, int stepCost_in, int pathCost_in, Vector pathHistory_in, String actionHistory_in) {
		this.column = column_in;
		this.row = row_in;
		this.stepCost = stepCost_in;
		this.pathCost = pathCost_in;
		this.pathHistory = pathHistory_in;
		this.actionHistory = actionHistory_in;
	}

	//Overrides the compare function
	//Used when soerting the priority queue
	//Compare path costs
	//Smaller path costs should be at the start of the queue
	public int compare(Cell cell, Cell secondCell) {
		return cell.pathCost - secondCell.pathCost;
	}
}