package ie.gmit.sw.ai;

//import java.awt.Color;
import java.util.ArrayList;

import ie.gmit.sw.maze.Node;

public class IterDFS implements EnemyAI
{

	private Node[][] maze;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	
	public void traverse(Node[][] maze, Node start) {
		this.maze = maze;
		int limit = 9;
		
		while(keepRunning)
		{
			dfs(start, 0, limit);
			
			if (keepRunning)
			{
//				try 
//				{ //Pause before next iteration
//					Thread.sleep(1);
		      		limit++;       		
		      		unvisit();	
//				} 
//				catch (InterruptedException e) 
//				{
//					e.printStackTrace();
//				}			
			}
      	}
	}

	private void dfs(Node node, int depth, int limit)
	{
		if (!keepRunning || depth > limit) 
		{
			return;		
		}
		node.setVisited(true);	
		node.setNodeType('V');
		
		if (node.isGoalNode())
		{
			System.out.println("FOUND GOAL");
	        time = System.currentTimeMillis() - time; //Stop the clock
	        keepRunning = false;
			return;
		}
		
//		try 
//		{ //Simulate processing each expanded node
//			Thread.sleep(1);
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
		
		ArrayList<Node> children = node.adjacentNodes(maze);
		
		for (Node child : children) 
		{
			//System.out.println(child.toString() + " :::: " + child.getNodeType());
			if(child.getNodeType() == ' '  ||  child.getNodeType() == 'G' || child.getNodeType() == 'V'  ||  child.getNodeType() == 'E')
			{
				//System.out.println("VALID");
				if (child != null && !child.isVisited())
				{
					child.setParent(node);
					dfs(child, depth + 1, limit);
				}
			}
		}
	} 
		
	private void unvisit()
	{
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				maze[i][j].setVisited(false);
				maze[i][j].setParent(null);
				//maze[i][j].setNodeType(' ');
			}
		}
	}
}
