package ie.gmit.sw.maze;

import ie.gmit.sw.maze.Node.Direction;

public class BinaryTreeMaze extends AbstractMazeGenerator
{
	private Node [][] maze;
	public BinaryTreeMaze(int rows, int cols) 
	{
		super(rows, cols);
		initi();
		generateMaze();

		int featureNumber = (int)((rows * cols) * 0.01);
		
		addFeature('W', 'X', featureNumber);
		addFeature('?', 'X', featureNumber);
		addFeature('B', 'X', featureNumber);
		addFeature('H', 'X', featureNumber);
		

	}

	@Override
	public void generateMaze()
	{
		this.maze = super.getMaze();
		
		for (int row = 1; row < maze.length -1; row++)
		{
			for (int col = 1; col < maze[row].length -1; col++)
			{
				int num = (int) (Math.random() * 10);
				if (col > 0 && (row == 0 || num >= 5))
				{
					maze[row][col].addPath(Node.Direction.West);
					maze[row-1][col +1 ].setNodeType(' ');//W
				}
				else if (col < 0 && (row != 0 || num >= 5))
				{
					maze[row][col].addPath(Node.Direction.East);
					maze[row +1][col].setNodeType(' ');//W
				}
				else
				{
					maze[row][col].addPath(Node.Direction.North);
					maze[row + 1][col-1].setNodeType(' '); //N
				}				
			}
		}
		super.setGoalNode();
		super.setMaze(this.maze);
	}
	
	private void initi()
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				maze[row][col].setNodeType('X');
			}
		}
	}

	private void addFeature(char feature, char replace, int number)
	{
		int counter = 0;
		while (counter < feature)
		{
			int row = (int)(maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace)
			{
				maze[row][col].setNodeType(feature);
				counter++;
			}
		}
	}
	public Node[][] getMaze()
	{
		return this.maze;
	}
}