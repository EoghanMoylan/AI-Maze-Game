package ie.gmit.sw.maze;

import java.util.*;

public class EllersMaze implements MazeGenerator 
{
	private Node[][] maze;
	private Random randNum = new Random();
	private Set<Set<Node>> tempSet = new HashSet<Set<Node>>();
	
	public Node[][] getMaze() 
	{
		return this.maze;
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
	public void buildMaze(int rows, int cols)
	{
		maze = new Node[rows][cols];
		
		init();
		
		for (int row = 1; row < maze.length - 1; row ++)
		{
			for (int col = 2; col < maze[row].length - 2; col+=2)
			{
				//add all columns in first row to their first set
				if(row == 1)
				{
					maze[row][col].NewSet();
					maze[row][col].addNodeToSet(maze[row][col]);
				}
				//if not in a set on the row above it
				else if (row > 1 && onSet(maze[row][col]))
				{
					maze[row][col].NewSet();
					maze[row][col].addNodeToSet(maze[row][col]);
				}
				//randomly adds adjacent nodes to set if not 
				//already on set
				if ((randNum.nextInt(2)) == 1)
				{
					if(maze[row][col].getNodeSet() != null)
					{
						if(!(maze[row][col].getNodeSet().contains(maze[row][col + 1])))
						{
							maze[row][col].addNodeToSet(maze[row][col + 1]);
							//maze[row][col].addNodeToSet(maze[row][col + 2]);
							maze[row][col + 1].setNodeType(' ');
							//maze[row][col + 2].setNodeType(' ');
							//maze[row][col].mergeSets(maze[row][col + num].getNodeSet());
						}
					}
				}
				if(maze[row][col].getNodeSet() != null)
				{
					tempSet.add(maze[row][col].getNodeSet());
				}
			}
		}
		doVerticals(tempSet);
	}
	private void doVerticals(Set<Set<Node>> tempSet)
	{
		for(Set<Node> nodeSet : tempSet)
		{
			int vertCount = 0;
			while(vertCount == 0)
			{
				System.out.println(vertCount);
				for(Node n : nodeSet)
				{
					if(randNum.nextInt(2) == 1)
					{
						maze[n.getRow() + 1][n.getCol()].setNodeType(' ');
						vertCount++;
						break;
					}
				}
			}
		}
	}
	
	private boolean onSet(Node n)
	{
		boolean available = true;
		for (int row = 1; row < maze.length - 1; row ++)
		{
			for (int col = 1; col < maze[row].length - 1; col++)
			{
				if(maze[row][col].getNodeSet() != null)
				{
					if(maze[row][col].getNodeSet().contains(n))
					{
						available = false;
					}
				}
			}
		}
		return available;
	}
}
