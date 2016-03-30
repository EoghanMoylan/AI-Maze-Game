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
		
		for (int row = 1; row < maze.length -1 ; row ++)
		{
			for (int col = 1; col < maze[row].length -1; col++)
			{
				int num = (int) (Math.random() * 10);
				
				if (col > 0 && (num >= 5))
				{
					maze[row][col].addPath(Node.Direction.West);
					maze[row - 1][col].setNodeType(' ');		
				}
				else
				{
					maze[row][col].addPath(Node.Direction.North);
					maze[row][col + 1].setNodeType(' ');
				}
			}
		}
		//createPaths();
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
		while (counter < number)
		{
			int row = (int)(maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() != ' ')
			{
				maze[row][col].setNodeType(feature);
				counter++;
			}
		}
	}
//	private void createPaths()
//	{
//		for (int row = 1; row < maze.length -1; row++)
//		{
//			for (int col = 1; col < maze[row].length -1; col++)
//			{
//				Direction[] dir = maze[row][col].getPaths();
//				for(Direction direc : dir)
//				{
//					
//					System.out.println(direc.toString());
//					
//					switch(direc)
//					{
//						case North: 
//							maze[row - 1][col].setNodeType(' ');
//							if(maze[row][col -1].getNodeType() == ' ')
//							{
//								System.out.println("NORTH NOTICE");
//								maze[row][col - 1].setNodeType('X');
//							}
//							break;
//						case West:			
//							maze[row][col - 1].setNodeType(' ');
//							
//							if(maze[row -1][col].getNodeType() == ' ')
//							{
//								System.out.println("West NOTICE");
//								maze[row + 1][col].setNodeType('X');
//							}
//								break;
//						default:
//							break;
//					}
//				}
//			}
//		}	
//	}
	public Node[][] getMaze()
	{
		return this.maze;
	}
}