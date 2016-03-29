package ie.gmit.sw.maze;

public class MazeGenerator 
{
	private Node[][] maze;
	private int rows;
	private int cols;
	public MazeGenerator(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		buildMaze();
	}	
	public Node[][] getMaze()
	{
		return this.maze;
	}

	private void buildMaze()
	{
		BinaryTreeMaze btMaze = new BinaryTreeMaze(rows, cols);
		this.maze = btMaze.getMaze();
	}	
}