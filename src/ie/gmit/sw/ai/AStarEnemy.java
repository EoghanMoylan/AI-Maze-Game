package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.PriorityQueue;

import ie.gmit.sw.maze.Node;

public class AStarEnemy implements EnemyAI
{
	private Node goal;
	public AStarEnemy(Node goal)
	{
		this.goal = goal;
	}
	public void traverse(Node[][] maze, Node node)
	{
        long time = System.currentTimeMillis();
        
		PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		java.util.List<Node> closed = new ArrayList<Node>();
    	   	
		open.offer(node);
		node.setPathCost(0);	
		Node oldNode = node;
		while(!open.isEmpty())
		{
			oldNode.setNodeType(' ');
			System.out.println("LOOKING");
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			node.setNodeType('V');
			oldNode = node;
			System.out.println(node.toString());
			
			if (node.isGoalNode())
			{
				System.out.println("goalNode founds");
		        time = System.currentTimeMillis() - time; //Stop the clock
				break;
			}
			
			try 
			{ 
				//Simulate processing each expanded node
				Thread.sleep(1);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			//Process adjacent nodes
			ArrayList<Node> children = node.adjacentNodes(maze);
			for (Node child : children) 
			{
				if(child.getNodeType() == ' ')
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
						child.setPathCost(node.getPathCost() + 1);
						open.add(child);
					}
				}
			}	
		}
	}
}