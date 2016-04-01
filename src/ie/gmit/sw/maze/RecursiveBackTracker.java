package ie.gmit.sw.maze;

import java.util.*;

public class RecursiveBackTracker implements MazeGenerator
{

	private Node[][] maze;
	private Random randNum = new Random();
	private List<Node> startingCells = new ArrayList<Node>();
	
	public Node[][] getMaze() 
	{
		return this.maze;
	}
	public void buildMaze(int rows, int cols) 
	{
		maze = new Node[rows][cols];
		init();
		int randNode = randNum.nextInt(startingCells.size()-1);
		Node startNode = startingCells.get(randNode);
		Node thisNode = startNode;
		Stack<Node> nodes = new Stack<Node>();
		boolean firstNode = true;
		ArrayList<Node> adjNodes = new ArrayList<Node>();
		ArrayList<Node> validNeighbours = new ArrayList<Node>();
		nodes.push(thisNode);
		do
		{
			if(thisNode.isStart() == true)
			{
				adjNodes = thisNode.adjacentNodesFirst(maze);
				firstNode = false;
			}
			else
			{
				adjNodes = thisNode.adjacentNodes(maze);
			}			
			for(Node n : adjNodes)
			{
				if(!(n.isVisited()))
				{
					validNeighbours.add(n);
				}
			}
			if(validNeighbours.size() > 0)
			{			
				Node next = validNeighbours.get(randNum.nextInt(validNeighbours.size()));
				
				Node wall = getWall(thisNode, maze[next.getRow()][next.getCol()]);
				wall.setNodeType(' ');
				nodes.push(thisNode);
				thisNode.setVisited(true);
				
				thisNode = next;	
				thisNode.setVisited(true);

				validNeighbours.clear();
			}
			else if (!nodes.isEmpty())
			{
				Node newOne = nodes.pop();
				thisNode = newOne;
			}
		} while(thisNode != startNode && !nodes.isEmpty());
	}
	private Node getWall(Node n1, Node n2)
	{
		Node wall;
		if (n1.getRow() == n2.getRow()) 
		{
			if (n1.getCol() < n2.getCol())
			{
				wall = maze[n2.getRow()][n2.getCol()-1];
			}
			else
			{
				wall = maze[n2.getRow()][n2.getCol()+1];
			}
			
		} 
		else 
		{
			if (n1.getRow() < n2.getRow())
			{
				wall = maze[n2.getRow()-1][n2.getCol()];

			}
			else
			{
				wall = maze[n2.getRow()+1][n2.getCol()];
			}
		}		
		return wall;
	}
	
	private void init()
	{
		for (int row = 0; row < maze.length; row ++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				maze[row][col] = new Node(row, col);
				if(row % 2 == 0 || col % 2 == 0)
				{
					maze[row][col].setNodeType('X');
				}
				else
				{
					maze[row][col].setNodeType(' ');
					maze[row][col].setStart(true);
					startingCells.add(maze[row][col]);
				}
			}
		}
	}
}
