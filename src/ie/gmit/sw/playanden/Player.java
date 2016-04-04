package ie.gmit.sw.playanden;

import ie.gmit.sw.maze.Node;

public class Player 
{
	private Node currentNode;
	private int health;
	private boolean isArmed;
	private int numberOfWeapons;
	public Node getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isArmed() {
		return isArmed;
	}
	public void setArmed(boolean isArmed) {
		this.isArmed = isArmed;
	}
	public int getNumberOfWeapons() {
		return numberOfWeapons;
	}
	public void setNumberOfWeapons(int numberOfWeapons) {
		this.numberOfWeapons = numberOfWeapons;
	}

}
