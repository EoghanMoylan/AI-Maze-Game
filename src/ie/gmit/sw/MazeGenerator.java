package ie.gmit.sw;

public class MazeGenerator 
{
	private char[][] maze;
	public MazeGenerator(int rows, int cols)
	{
		maze = new char[rows][cols];
		init();
		buildMaze();
		
		int featureNumber = (int)((rows * cols) * 0.01);
		
		addFeature('W', 'X', featureNumber);
		addFeature('?', 'X', featureNumber);
		addFeature('B', 'X', featureNumber);
		addFeature('H', 'X', featureNumber);
	}	
	public char[][] getMaze()
	{
		return this.maze;
	}
	
	private void init()
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				maze[row][col] = 'X';
			}
		}
	}

	private void addFeature(char feature, char replace, int number)
	{
		int counter = 0;
		while (counter < feature)
		{
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col] == replace){
				maze[row][col] = feature;
				counter++;
			}
		}
	}
	
	private void buildMaze()
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze[row].length - 1; col++)
			{
				int num = (int) (Math.random() * 10);
				if (num >= 5 && col + 1 < maze[row].length - 1)
				{
					maze[row][col + 1] = ' ';
					continue;
				}
				if (row + 1 < maze.length)
				{ //Check south
					maze[row + 1][col] = ' ';
				}				
			}
		}	
	}
	
	
}
