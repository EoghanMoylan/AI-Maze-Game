package ie.gmit.sw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import ie.gmit.sw.ai.*;
import ie.gmit.sw.helpfulai.AStarHelp;
import ie.gmit.sw.helpfulai.DFSBomb;
import ie.gmit.sw.maze.*;
import ie.gmit.sw.playanden.Enemy;
import ie.gmit.sw.playanden.Enemy.SearchType;
import ie.gmit.sw.playanden.Player;


public class Runner implements KeyListener
{
	private static final int MAZE_DIMENSION = 60;
	private Node[][] model;
	private int currentRow;
	private int currentCol;
	private GameView view;
	private Node endGoal;
	private Player player;
	
	public Runner() throws Exception
	{	
		//only one instance of the player and is passed around between classes
		player = new Player();
		//new recursive back tracker maze type. Others not working fully
		MazeGenerator m = new RecursiveBackTracker();
		m.buildMaze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getMaze();
		endGoal = m.getGoalNode();
    	view = new GameView(model);
    	view.addPlayer(player);
    	placePlayer();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        setUpEnemies();
	}
	
	private void placePlayer()
	{   	
		//places the player only in a section of the maze with the floor type
		//this is to avoid the deletion of walls and therefore loops
		boolean isValid = false;
		while(!isValid)
		{
			currentRow = (int) (MAZE_DIMENSION * Math.random());
			currentCol = (int) (MAZE_DIMENSION * Math.random());
			if(model[currentRow][currentCol].getNodeType() == ' ')
			{
				isValid = true;
			}
		}
    	model[currentRow][currentCol].setNodeType('E');
    	player.setCurrentNode(model[currentRow][currentCol]);
    	updateView(); 		
	}
	
	private void updateView()
	{
		//looks camera movement if player health is below 0
		if(player.getHealth() <= 0)
		{
			end();
		}
		else
		{
			view.setCurrentRow(currentRow);
			view.setCurrentCol(currentCol);
			player.setCurrentNode(model[currentRow][currentCol]);
			//System.out.println(player.getCurrentNode());
		}
	}

	public void setUpEnemies()
	{
		//Creates 7 different threads each with a new enemy
		//as with player will only spawn enemy on node with floor type
		//relatively evenly assigns iterative deepening and A* algorithms to enemies
		for(int i = 1 ; i <= 7 ; i++)
		{
			Enemy.SearchType search;
			if(i % 2 == 0)
			{
				search = SearchType.ITERDFS;
			}
			else
			{
				search = SearchType.ASTAR;
			}
			int tempRow = 2;
			int tempCol = 2;
			boolean isValid = false;
			while(!isValid)
			{
				tempRow = (int) (MAZE_DIMENSION * Math.random());
				tempCol = (int) (MAZE_DIMENSION * Math.random());
				
				if(model[tempRow][tempCol].getNodeType() == ' ' && model[tempRow][tempCol].getNodeType() != 'E' )
				{
					isValid = true;
				}
			}
			int finalRow = tempRow;
			int finalCol = tempCol;
			Thread enemy = new Thread() 
			{
			    public void run() 
			    {
			    	Enemy enemy = new Enemy(player, search, model[finalRow][finalCol], model);
			    	while(!enemy.isComplete())
			    	{
				        try 
				        { 
				        	System.out.println("NEW ENEMY : " + search + " TYPE");        	
				        	enemy.initHunter();
				        } 
				        catch(Exception e) 
				        {
				            System.out.println(e);
				        }
			    	}
			    	return;
			    }  
			    
			};
			enemy.start();
		}
	}
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) 
        {
        	if (isValidMove(currentRow, currentCol + 1)) 
        	{
        		currentCol++;   		
        	}
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) 
        {
        	if (isValidMove(currentRow, currentCol - 1)) 
        	{
        		currentCol--;	
        	}
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) 
        {
        	if (isValidMove(currentRow - 1, currentCol))
    		{
    			currentRow--;
    		}
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) 
        {
        	if (isValidMove(currentRow + 1, currentCol)) 
    		{
    			currentRow++;        	  		
    		}
        }
        else if (e.getKeyCode() == KeyEvent.VK_Z)
        {
        	view.toggleZoom();
        }
        else
        {
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int r, int c)
	{
		//calculates if next node has floor type, and if not triggers the event of the node (if event is there)
		if (r <= model.length - 1 && c <= model[r].length - 1 && (model[r][c].getNodeType() == ' ' ||model[r][c].getNodeType() == 'C'))
		{
			model[currentRow][currentCol].setNodeType(' ');
			model[r][c].setNodeType('E');
			return true;
		}
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getNodeType() == 'G')
		{
			end();
			return false;
		}
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getNodeType() == '?')
		{
			model[r][c].setNodeType('X');
			Thread help = new Thread() 
			{
			    public void run() 
			    {
			    	//System.out.println("RUNNING");
			        try 
			        { 
			        	//System.out.println("NEW THREAD RUNNING");
						AI helper = new AStarHelp(endGoal);
						helper.traverse(model,model[currentRow][currentCol]);
			        } 
			        catch(Exception e) 
			        {
			            System.out.println(e);
			        }
			        return;
			    } 
			    
			};
			help.start();
			//help.interrupt();
			return false;
		}
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getNodeType() == 'B')
		{
			model[r][c].setNodeType('X');
			Thread bomb = new Thread() 
			{
			    public void run() 
			    {
			    	//System.out.println("RUNNING");
			        try 
			        { 
						
						DFSBomb dfsbomb = new DFSBomb(6);
						dfsbomb.traverse(model, model[currentRow][currentCol]);
			        } 
			        catch(Exception e) 
			        {
			            System.out.println(e);
			        }
			        return;
			    } 
			    
			};
			bomb.start();
			return false;
		}
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getNodeType() == 'W')
		{
			model[r][c].setNodeType('X');
			player.setArmed(true);
			player.setNumberOfWeapons(player.getNumberOfWeapons() + 1);
			return false;
		}
		else
		{
			return false; //Can't move
		}
	}
	public void end()
	{
		//ends the game by triggering the end screen and closing the window
		//used upon death and on reaching the goal node
		view.triggerEndScreen();
		try 
		{ 
			//Simulate processing each expanded node
			Thread.sleep(5000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(0);
		
	}
	public static void main(String[] args) throws Exception 
	{
		new Runner();
	}
	
}
