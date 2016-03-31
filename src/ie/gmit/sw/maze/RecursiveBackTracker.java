package ie.gmit.sw.maze;

import java.util.*;

public class RecursiveBackTracker implements MazeGenerator
{

	private Node[][] maze;
	private Random randNum = new Random();
	
	public Node[][] getMaze() 
	{
		return this.maze;
	}
	public void buildMaze(int rows, int cols) 
	{
		maze = new Node[rows][cols];
		init();
		int curRow = 1;
		int curCol = 1;
		Stack<Node> nodes = new Stack<Node>();
		boolean firstNode = true;
		ArrayList<Node> adjNodes = new ArrayList<Node>();
		ArrayList<Node> validNeighbours = new ArrayList<Node>();
		while(nodesUnvisited())
		{
			
					
			if(firstNode = true)
			{
				adjNodes = maze[curRow][curCol].adjacentNodesFirst(maze);
				firstNode = false;
			}
			else
			{
				System.out.println("NOT FIRST");
				adjNodes = maze[curRow][curCol].adjacentNodes(maze);
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
				
				Node wall = getWall(maze[curRow][curRow], maze[next.getRow()][next.getCol()]);
				wall.setNodeType(' ');
				nodes.push(maze[curRow][curCol]);
				maze[curRow][curCol].setVisited(true);
				
				curRow = next.getRow();
				curCol = next.getCol();	
				maze[curRow][curCol].setVisited(true);
				System.out.println("THE NEIGHS");
				System.out.println(validNeighbours.size());
				validNeighbours.clear();
				System.out.println(validNeighbours.size());
			}
			else
			{
				System.out.println("NEW SHTUFF ");
				Node newOne = nodes.pop();
				curRow = newOne.getRow();
				curCol = newOne.getCol();	
			}
		}
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
		System.out.println("GIT DAT WALL");
		
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
				}
			}
		}
	}
	private boolean nodesUnvisited()
	{
		boolean unvisited = true;
		int counter = 0;
		for (int row = 0; row < maze.length; row ++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				if(maze[row][col].isVisited() == false)
				{
					unvisited = true;
					counter ++;
					//break;
				}
				else
				{
					unvisited = false;
				}
			}
		}
		System.out.println(counter);
		return unvisited;
		
	}

}
