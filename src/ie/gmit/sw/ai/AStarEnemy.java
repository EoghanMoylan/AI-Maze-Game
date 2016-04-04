package ie.gmit.sw.ai;

import java.util.*;

import ie.gmit.sw.maze.Node;

public class AStarEnemy extends AStar 
{
//	private Node goal;
	//private List<Node> closed = new ArrayList<Node>();
	private List<Node> finalList; //= new ArrayList<Node>();
	//private PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
	public AStarEnemy(Node goal)
	{
		super(goal);
	}
	public void updateGoalNode(Node goal)
	{
		super.updateGoalNode(goal);
	}
	public void traverse(Node[][] maze, Node node)
	{
		super.traverse(maze, node);
		finalList = super.returnList();
		go();
	}
	public void go()
	{
		List<Node> newList = new ArrayList<Node>();
		//System.out.println("GO GO GO");
		Node oldNode;
		Node curNode = finalList.get(finalList.size()-1);
		while(curNode != null)
		{
			newList.add(curNode);
			curNode = curNode.getParent();
		}
		for(int i = newList.size() -1 ; i >= 1 ; i--)
		{
			curNode = newList.get(i);
			if(curNode != null)
			{
				if(curNode.getNodeType() == 'G')
				{
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
			if(oldNode.getNodeType() != 'X' && oldNode.getNodeType() != 'G')
			{
				oldNode.setNodeType(' ');
			}
		}
		super.clearAll();
	}
}