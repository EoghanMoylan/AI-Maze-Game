package ie.gmit.sw.ai;

//import java.awt.Color;
import java.util.*;

import ie.gmit.sw.maze.Node;

public class EnemyIterDFS implements AI
{
	private Node[][] maze;
	private Node goal;
	private boolean keepRunning = true;
	private List<Node> finalList = new ArrayList<Node>();
	private List<Node> newList = new ArrayList<Node>();
	private Node finalNode = new Node(0, 0);

	public void traverse(Node[][] maze, Node start) 
	{
		//maze = maze.clone();
		this.maze = maze;
		int limit = 2;
		
		while(keepRunning)
		{
			dfs(start, 0, limit);
			
			if (keepRunning)
			{
	      		limit++;       		
	      		unvisit();			
			}
      	}
		
		go();
	}

	private void dfs(Node node, int depth, int limit)
	{
		if (!keepRunning || depth > limit) 
		{
			return;		
		}
		node.setVisited(true);	
		
		if (node == goal)
		{
			System.out.println("FOUND GOAL");
			goal = node;
	        keepRunning = false;
			return;
		}
		
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
					finalList.add(child);
					dfs(child, depth + 1, limit);
				}
			}
		}
	} 
	public void go()
	{
		finalNode.setHasPlayer(false);
//		System.out.println("GO GO GO");
		Node oldNode;
//		Node curNode = finalList.get(finalList.size()-1);
		Node curNode = goal;
		while(curNode != null)
		{
		//	System.out.println("ADDING");
			newList.add(curNode);
			curNode = curNode.getParent();
		}
		for(int i = newList.size() -1 ; i >= 0 ; i--)
		{
			//System.out.println(newList.size());
			curNode = newList.get(i);
			if(curNode != null)
			{
				//System.out.println(curNode.toString() + " " + curNode.getNodeType());
				
				if(curNode.getNodeType() == 'E' || curNode.getNodeType() == 'F')
				{
					if(curNode.getNodeType() == 'E')
					{
						curNode.setHasPlayer(true);
					}
					finalNode = curNode;
					break;
				}
				curNode.setNodeType('V');
			}

			try 
			{ 
				//Simulate processing each expanded node
				Thread.sleep(500);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			oldNode = curNode;
			if(oldNode.getNodeType() != 'X' && oldNode.getNodeType() != 'G' && oldNode.getNodeType() != 'C' && oldNode.getNodeType() != 'F')
			{
				oldNode.setNodeType(' ');
			}
		}
		
		//System.out.println(finalList);
		unvisit();
	}
		
	private void unvisit()
	{
		newList.clear();
		finalList.clear();
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
	public Node returnFinalNode()
	{
		return finalNode;	
	}
	@Override
	public void updateGoalNode(Node goal)
	{
		this.goal = goal;
		
	}
}
