A path-finding program that uses artificial intelligence-oriented methods.
Uses the A* method to find a path from a starting position to an end position on a grid.
A* uses the Manhattan heuristic.

RobotPlanner is the main driver file.
AStar and Cell are class files.

The program accepts five arguments; a filename (which contains the grid environment), and 4 integers specifying start state and goal start.
eg:
./RobotPlanner testgrid_large.txt 0 2 4 2
would plan a path from [0, 2] to [4, 2]
[0, 0] being the top left of a grid and [n - 1, n - 1] being the bottom right of a grid
for an n x n grid

The program outputs the list of actions required to get to the goal state.
eg:
U R R D D L L
where:
U = up
R = right
D = down
L = left

The grid environment is a text file.
The first line contains the width and height as two integers.
Each subsequent line contains a set of 0s and 1s.
0 represents that a cell is navigable.
1 represents that a cell cannot be passed through.
