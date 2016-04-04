package ie.gmit.sw.helpfulai;

import java.util.*;

import ie.gmit.sw.maze.Node;

public class DFSBomb
{
	private Node[][] maze;
	private int limit;
	private boolean keepRunning = true;
	private List<Node> finalList = new ArrayList<Node>();
	
	public DFSBomb(int limit){
		this.limit = limit;
	}
	
	public void traverse(Node[][] maze, Node node) {
		this.maze = maze;
		System.out.println("Search with limit " + limit);
		dfs(node, 1);
	}
	
	private void dfs(Node node, int depth)
	{
		if (!keepRunning || depth > limit) 
		{
			return;
		}
		
		node.setVisited(true);	

		ArrayList<Node> children = node.adjacentNodes(maze);
		
		for (Node child : children) 
		{
			//System.out.println(child.toString() + " :::: " + child.getNodeType());
			if(child.getNodeType() == ' '  ||  child.getNodeType() == 'G' || child.getNodeType() == 'V'  ||  child.getNodeType() == 'E')
			{
				//System.out.println("VALID");
				if (child != null && !child.isVisited())
				{
					child.setNodeType('F');
					
					child.setParent(node);
					finalList.add(child);

					dfs(child, limit + 1);
					System.out.println("WORKING STUFF");
				}
			}
		}
		try 
		{ 
			//Simulate processing each expanded node
			Thread.sleep(250);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		unvisit();
	}
	private void unvisit()
	{
		for(Node n : finalList)
		{
			n.setNodeType(' ');
		}
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				maze[i][j].setVisited(false);
				maze[i][j].setParent(null);
				//maze[i][j].setNodeType(' ');
			}
		}
		finalList.clear();
	}
}
