package ie.gmit.sw.maze;

public interface MazeGenerator 
{
	public Node[][] getMaze();
	public void buildMaze(int rows , int cols);
}