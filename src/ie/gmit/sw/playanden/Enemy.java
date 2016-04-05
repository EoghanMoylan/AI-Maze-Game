package ie.gmit.sw.playanden;

import ie.gmit.sw.ai.AI;
import ie.gmit.sw.ai.AStarEnemy;
import ie.gmit.sw.ai.EnemyIterDFS;
import ie.gmit.sw.maze.Node;

public class Enemy 
{
	public enum SearchType {ASTAR, ITERDFS};
	private boolean isAlive = true;
	private Player player;
	private SearchType search;
	private Node currentPos;
	private Node[][] maze;
	private AI hunter = null;
	private boolean complete = false;
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Enemy(Player player, SearchType search, Node startNode, Node[][] maze)
	{
		setPlayer(player);
		setSearchType(search);
		currentPos = startNode;
		this.maze = maze.clone();
	}
	
	public Node getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(Node currentPos)
	{
		this.currentPos = currentPos;
		
		if(currentPos.isHasPlayer() && currentPos.getNodeType() == 'E')
		{
			player.attack();
			setComplete(true);
			//Thread.currentThread().interrupt();
		}
		else if(currentPos.getNodeType() == 'F')
		{
			setComplete(true);
			//Thread.currentThread().interrupt();
		}
		else
		{
			hunt();
		}
	}
	public boolean isAlive() 
	{
		return isAlive;
	}
	public void setAlive(boolean isAlive) 
	{
		this.isAlive = isAlive;
	}
	public Player getPlayer() 
	{
		return player;
	}
	public void setPlayer(Player playerPos) 
	{
		this.player = playerPos;
	}
	public SearchType getSearchType() 
	{
		return search;
	}
	public void setSearchType(SearchType searchType) 
	{
		this.search = searchType;
	}
	public void initHunter()
	{
		if(search == SearchType.ASTAR)
		{
			hunter = new AStarEnemy(player.getCurrentNode());
		}
		else if(search == SearchType.ITERDFS)
		{
			hunter = new EnemyIterDFS();
		}
		hunt();
	}
	public void hunt()
	{
		updatePlayerPos();
		hunter.traverse(maze, currentPos);
		setCurrentPos(hunter.returnFinalNode());
	}
	public void updatePlayerPos()
	{
		//System.out.println(player.getCurrentNode());
		hunter.updateGoalNode(player.getCurrentNode());
	}
}