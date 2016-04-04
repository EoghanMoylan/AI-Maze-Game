package ie.gmit.sw.ai;

import java.util.*;

import ie.gmit.sw.maze.Node;

public class AStarEnemy implements EnemyAI
{
	private Node goal;
	private List<Node> closed = new ArrayList<Node>();
	private List<Node> finalList = new ArrayList<Node>();
	private PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
	public AStarEnemy(Node goal)
	{
		this.goal = goal;
	}
	public void traverse(Node[][] maze, Node node)
	{
        long time = System.currentTimeMillis();
        
		
    	   	
		open.offer(node);
		node.setPathCost(0);			
		while(!open.isEmpty())
		{
			//System.out.println("LOOKING");
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			//System.out.println(node.toString());
			
			if (node.isGoalNode())
			{
				System.out.println(goal);
				System.out.println("goalNode founds");
		        time = System.currentTimeMillis() - time; //Stop the clock
				break;
			}
			//Process adjacent nodes
			ArrayList<Node> children = node.adjacentNodes(maze);
			for (Node child : children) 
			{
				//System.out.println(child.toString() + " :::: " + child.getNodeType());
				if(child.getNodeType() != 'X' && child.getNodeType() != 'W' && child.getNodeType() != 'B' && child.getNodeType() != 'H'&& child.getNodeType() != '?')//(child.getNodeType() == ' '  ||  child.getNodeType() == 'G' ||  child.getNodeType() == 'V' ||  child.getNodeType() == 'E')
				{
					int score = node.getPathCost() + 1 + child.getHeuristic(goal);
					int existing = child.getPathCost() + child.getHeuristic(goal);
					if ((open.contains(child) || closed.contains(child)) && existing < score)
					{
						continue;
					}
					else
					{
						open.remove(child);
						closed.remove(child);
						child.setParent(node);
						finalList.add(child);
						child.setPathCost(node.getPathCost() + 1);
						open.add(child);
					}
				}
			}	
		}
		
		go();
	}
	public void go()
	{
		List<Node> newList = new ArrayList<Node>();
		System.out.println("GO GO GO");
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
			if(oldNode.getNodeType() != 'X')
			{
				oldNode.setNodeType(' ');
			}
		}
	}
}