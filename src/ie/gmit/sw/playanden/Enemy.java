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
	private Node endNode;
	private Node[][] maze;
	private AI hunter = null;
	
	public Enemy(Player player, SearchType search, Node startNode, Node[][] maze)
	{
		setPlayer(player);
		endNode = player.getCurrentNode();
		setSearchType(search);
		setCurrentPos(startNode);
		this.maze = maze.clone();
	}
	
	public Node getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(Node currentPos) {
		this.currentPos = currentPos;
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
		hunter.traverse(maze, currentPos);
		if(endNode.getNodeType() == 'E')
		{
			updatePlayerPos();
			hunt();
		}
		System.out.println("HUNT DONE");
	}
	public void updatePlayerPos()
	{
		System.out.println(player.getCurrentNode());
		hunter.updateGoalNode(player.getCurrentNode());
	}
}