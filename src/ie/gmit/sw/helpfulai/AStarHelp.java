package ie.gmit.sw.helpfulai;

import java.util.*;
import ie.gmit.sw.ai.AStar;
import ie.gmit.sw.maze.Node;

public class AStarHelp extends AStar
{
	private List<Node> finalList;
	
	public AStarHelp(Node goal)
	{
		super(goal);
	}
	public void traverse(Node[][] maze, Node node)
	{
		super.traverse(maze, node);
		finalList = super.returnList();
		revealPath();
	}
	public void revealPath()
	{
		finalList = super.returnList();
		List<Node> newList = new ArrayList<Node>();
		Node curNode = finalList.get(finalList.size()-1);
		while(curNode != null)
		{
			newList.add(curNode);
			curNode = curNode.getParent();
		}
		System.out.println(newList);
		for(int i = newList.size() -1 ; i >= 1 ; i--)
		{
			curNode = newList.get(i);
			if(curNode != null)
			{
				if(curNode.getNodeType() == 'G')
				{
					break;
				}
				curNode.setNodeType('C');
				System.out.println(curNode.toString() + " " + curNode.getNodeType());
			}
		}
		try 
		{ 
			//Simulate processing each expanded node
			Thread.sleep(5000);
			//System.out.println("SHTUFF");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		for(int i = newList.size() -1 ; i >= 1 ; i--)
		{
			curNode = newList.get(i);
			if(curNode != null)
			{
				if(curNode.getNodeType() != 'G' && curNode.getNodeType() != 'X' && curNode.getNodeType() != 'E')
				{
					curNode.setNodeType(' ');
				}
			}
		}
		super.clearAll();
		//Thread.currentThread().interrupt();
	}
}
