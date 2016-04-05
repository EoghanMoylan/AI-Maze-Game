package ie.gmit.sw.ai;

import ie.gmit.sw.maze.Node;

public interface AI
{
	public void traverse(Node[][] maze, Node start);
	public void updateGoalNode(Node goal);
	public Node returnFinalNode();
}
