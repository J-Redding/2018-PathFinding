import java.util.*;

//Finds a path from a specified starting state to a goal state using the A* algorithm
//Uses the Manhattan heuristic
//Returns a list of actions required to arrive at the goal state
//Returns an error message if the goal cannot be reached
//Uses a priority queue to store the possible actions/cells
//Priority queue is sorted by the step cost + heuristic cost of the proposed action/cell
public class AStar {
	int width;
	int height;
	Vector grid;
	int startColumn;
	int startRow;
	int goalColumn;
	int goalRow;
	Vector fringe;

	public AStar(int width_in, int height_in, Vector grid_in, int startColumn_in, int startRow_in, int goalColumn_in, int goalRow_in) {
		this.width = width_in;
		this.height = height_in;
		this.grid = grid_in;
		this.startColumn = startColumn_in;
		this.startRow = startRow_in;
		this.goalColumn = goalColumn_in;
		this.goalRow = goalRow_in;
		this.fringe = new Vector();
	}

	//Check if the starting point is navigable, i.e. the cell is a 0
	public boolean startNavigable() {
		//Check that the starting position is on the grid
		if (this.startColumn < 0) {
			return false;
		}

		if (this.startRow < 0) {
			return false;
		}

		if (this.startColumn >= this.width) {
			return false;
		}

		if (this.startRow >= this.height) {
			return false;
		}

		//Check that the starting position is not a 1
		if (((int)(((Vector)(this.grid.elementAt(this.startRow))).elementAt(this.startColumn))) == 1) {
			return false;
		}

		return true;
	}

	//Check if the goal is navigable, i.e. the cell is a 0
	public boolean goalNavigable() {
		//Check that the goal position is on the grid
		if (this.goalColumn < 0) {
			return false;
		}

		if (this.goalRow < 0) {
			return false;
		}

		if (this.goalColumn >= this.width) {
			return false;
		}

		if (this.goalRow >= this.height) {
			return false;
		}

		//Check that the goal position is not a 1
		if (((int)(((Vector)(this.grid.elementAt(this.goalRow))).elementAt(this.goalColumn))) == 1) {
			return false;
		}

	 	return true;
	}

	//Check to see if the goal has been reached
	public boolean goalReached() {
		if (((Cell)(this.fringe.firstElement())).column == this.goalColumn) {
			if (((Cell)(this.fringe.firstElement())).row == this.goalRow) {
				return true;
			}
		}

		return false;
	}

	//Check if the cell above the current cell can be visited
	//If it can, add it to the fringe
	public void moveUp() {
		//Check that moving upwards doesn't go off the edge of the grid
		if (((Cell)(this.fringe.firstElement())).row != 0) {
			//Check that the cell is navigable
			if (((int)(((Vector)(this.grid.elementAt(((Cell)(this.fringe.firstElement())).row - 1))).elementAt(((Cell)(this.fringe.firstElement())).column))) != 1) {
				String nextPosition = ((Cell)(this.fringe.firstElement())).column + "," + (((Cell)(this.fringe.firstElement())).row - 1);
				//Check that the proposed cell has not been visited before
				//Note that this only checks the current path's history. This means that we can expand the same node in another path
				if (!((Cell)(this.fringe.firstElement())).pathHistory.contains(nextPosition)) {
					int stepCost = ((Cell)(this.fringe.firstElement())).stepCost + 1;
					//Using the Manhattan heuristic
					//Total distance in both x and y dimensions
					int columnDistance = Math.abs(this.goalColumn - ((Cell)(this.fringe.firstElement())).column);
					int rowDistance = Math.abs(this.goalRow - ((Cell)(this.fringe.firstElement())).row);
					int heuristicCost = columnDistance + rowDistance;
					//Add the current cell to the path history
					Vector nextPathHistory = ((Cell)(this.fringe.firstElement())).pathHistory;
					String thisPosition = ((Cell)(this.fringe.firstElement())).column + "," + ((Cell)(this.fringe.firstElement())).row;
					nextPathHistory.add(thisPosition);
					//Add the next cell to the fringe
					this.fringe.add(new Cell(((Cell)(this.fringe.firstElement())).column, ((Cell)(this.fringe.firstElement())).row - 1, stepCost, stepCost + heuristicCost, nextPathHistory, 
						((Cell)(this.fringe.firstElement())).actionHistory + "U "));
				}
			}
		}
	}

	//Check if the cell to the right of the current cell can be visited
	//If it can, add it to the fringe
	public void moveRight() {
		//Check that moving right doesn't go off the edge of the grid
		if (((Cell)(this.fringe.firstElement())).column != this.width - 1) {
			//Check that the cell is navigable
			if (((int)(((Vector)(this.grid.elementAt(((Cell)(this.fringe.firstElement())).row))).elementAt(((Cell)(this.fringe.firstElement())).column + 1))) != 1) {
				String nextPosition = (((Cell)(this.fringe.firstElement())).column + 1) + "," + ((Cell)(this.fringe.firstElement())).row;
				//Check that the proposed cell has not been visited before
				//Note that this only checks the current path's history. This means that we can expand the same node in another path
				if (!((Cell)(this.fringe.firstElement())).pathHistory.contains(nextPosition)) {
					int stepCost = ((Cell)(this.fringe.firstElement())).stepCost + 1;
					//Using the Manhattan heuristic
					//Total distance in both x and y dimensions
					int columnDistance = Math.abs(this.goalColumn - ((Cell)(this.fringe.firstElement())).column);
					int rowDistance = Math.abs(this.goalRow - ((Cell)(this.fringe.firstElement())).row);
					int heuristicCost = columnDistance + rowDistance;
					//Add the current cell to the path history
					Vector nextPathHistory = ((Cell)(this.fringe.firstElement())).pathHistory;
					String thisPosition = ((Cell)(this.fringe.firstElement())).column + "," + ((Cell)(this.fringe.firstElement())).row;
					nextPathHistory.add(thisPosition);
					//Add the next cell to the fringe
					this.fringe.add(new Cell(((Cell)(this.fringe.firstElement())).column + 1, ((Cell)(this.fringe.firstElement())).row, stepCost, stepCost + heuristicCost, nextPathHistory, 
						((Cell)(this.fringe.firstElement())).actionHistory + "R "));
				}
			}
		}
	}

	//Check if the cell below the current cell can be visited
	//If it can, add it to the fringe
	public void moveDown() {
		//Check that moving down doesn't go off the edge of the grid
		if (((Cell)(this.fringe.firstElement())).row != this.height - 1) {
			//Check that the cell is navigable
			if (((int)(((Vector)(this.grid.elementAt(((Cell)(this.fringe.firstElement())).row + 1))).elementAt(((Cell)(this.fringe.firstElement())).column))) != 1) {
				String nextPosition = ((Cell)(this.fringe.firstElement())).column + "," + (((Cell)(this.fringe.firstElement())).row + 1);
				//Check that the proposed cell has not been visited before
				//Note that this only checks the current path's history. This means that we can expand the same node in another path
				if (!((Cell)(this.fringe.firstElement())).pathHistory.contains(nextPosition)) {
					int stepCost = ((Cell)(this.fringe.firstElement())).stepCost + 1;
					//Using the Manhattan heuristic
					//Total distance in both x and y dimensions
					int columnDistance = Math.abs(this.goalColumn - ((Cell)(this.fringe.firstElement())).column);
					int rowDistance = Math.abs(this.goalRow - ((Cell)(this.fringe.firstElement())).row);
					int heuristicCost = columnDistance + rowDistance;
					//Add the current cell to the path history
					Vector nextPathHistory = ((Cell)(this.fringe.firstElement())).pathHistory;
					String thisPosition = ((Cell)(this.fringe.firstElement())).column + "," + ((Cell)(this.fringe.firstElement())).row;
					nextPathHistory.add(thisPosition);
					//Add the next cell to the fringe
					this.fringe.add(new Cell(((Cell)(this.fringe.firstElement())).column, ((Cell)(this.fringe.firstElement())).row + 1, stepCost, stepCost + heuristicCost, nextPathHistory, 
						((Cell)(this.fringe.firstElement())).actionHistory + "D "));
				}
			}
		}
	}

	//Check if the cell to the left of the current cell can be visited
	//If it can, add it to the fringe
	public void moveLeft() {
		//Check that moving left doesn't go off the edge of the grid
		if (((Cell)(this.fringe.firstElement())).column != 0) {
			//Check that the cell is navigable
			if (((int)(((Vector)(this.grid.elementAt(((Cell)(this.fringe.firstElement())).row))).elementAt(((Cell)(this.fringe.firstElement())).column - 1))) != 1) {
				String nextPosition = (((Cell)(this.fringe.firstElement())).column - 1) + "," + ((Cell)(this.fringe.firstElement())).row;
				//Check that the proposed cell has not been visited before
				//Note that this only checks the current path's history. This means that we can expand the same node in another path
				if (!((Cell)(this.fringe.firstElement())).pathHistory.contains(nextPosition)) {
					int stepCost = ((Cell)(this.fringe.firstElement())).stepCost + 1;
					//Using the Manhattan heuristic
					//Total distance in both x and y dimensions
					int columnDistance = Math.abs(this.goalColumn - ((Cell)(this.fringe.firstElement())).column);
					int rowDistance = Math.abs(this.goalRow - ((Cell)(this.fringe.firstElement())).row);
					int heuristicCost = columnDistance + rowDistance;
					//Add the current cell to the path history
					Vector nextPathHistory = ((Cell)(this.fringe.firstElement())).pathHistory;
					String thisPosition = ((Cell)(this.fringe.firstElement())).column + "," + ((Cell)(this.fringe.firstElement())).row;
					nextPathHistory.add(thisPosition);
					//Add the next cell to the fringe
					this.fringe.add(new Cell(((Cell)(this.fringe.firstElement())).column - 1, ((Cell)(this.fringe.firstElement())).row, stepCost, stepCost + heuristicCost, nextPathHistory, 
						((Cell)(this.fringe.firstElement())).actionHistory + "L "));
				}
			}
		}
	}

	//Find the set of actions that lead from the starting position to the goal position
	public String findPath() {
		if (!this.startNavigable()) {
			return("No path exists to the goal from the starting position");
		}

		if (!this.goalNavigable()) {
			return("No path exists to the goal from the starting position");
		}

		//Add the starting position to the fringe
		this.fringe.add(new Cell(this.startColumn, this.startRow, 0, 0, new Vector(), ""));
		//Search until the fringe is empty
		while (!this.fringe.isEmpty()) {
			if (this.goalReached()) {
				return(((Cell)(this.fringe.firstElement())).actionHistory);
			}

			//Check for move expansions clockwise arund current cell
			this.moveUp();
			this.moveRight();
			this.moveDown();
			this.moveLeft();
			//Remove the current cell from the fringe, as it has been expanded
			fringe.remove(0);
			//Sort the fringe, based on path cost
			Collections.sort(fringe, new Cell());
		}

		//There are no more possible actions
		//There is no path to the goal
		return("No path exists to the goal from the starting position");
	}
}