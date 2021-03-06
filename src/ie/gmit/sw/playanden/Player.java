package ie.gmit.sw.playanden;

import ie.gmit.sw.maze.Node;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Player 
{
	private Node currentNode;
	private int health = 100;
	private int playerSteps = 1;
	private boolean isArmed;
	private int numberOfWeapons;
	private String fileName = "FCL/Damage.fcl";
    private FIS fis = FIS.load(fileName,true);
	public int getSteps()
	{
		return playerSteps;
	}
	public Node getCurrentNode()
	{
		return currentNode;
	}
	public void setCurrentNode(Node currentNode) 
	{
		this.currentNode = currentNode;
		playerSteps++;
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
	public void attack()
	{
        // Error while loading?
        if( fis == null )
        { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        FunctionBlock functionBlock = fis.getFunctionBlock("fight");

        // Show 

        // Set inputs
        fis.setVariable("health", this.health);
        fis.setVariable("numberOfWeapons", this.numberOfWeapons);

        // Evaluate
        fis.evaluate();
      //  System.out.println("GOT HERE");
        // Show output variable's chart
        Variable damage = functionBlock.getVariable("damage");
        
        
        
        this.health -= damage.getValue();
        System.out.println(health);
        this.numberOfWeapons = 0;
        setArmed(false);
	}
}
