package ie.gmit.sw;

import java.awt.*;

import ie.gmit.sw.playanden.Player;

public class HUD
{
	private int DEFAULT_VIEW_SIZE;
	private boolean gameOver;
	public HUD(int DEFAULT_VIEW_SIZE)
	{
		this.DEFAULT_VIEW_SIZE = DEFAULT_VIEW_SIZE;
	}
	public void showHealth(Player p, Graphics2D g2)
	{
		// Implement a health bar based on player's current health from 0-100
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
		g2.setFont(font);
		FontMetrics f = g2.getFontMetrics(font);
        g2.setColor(Color.black);
        g2.fillRect(DEFAULT_VIEW_SIZE/2-100, 39, 101*2,12);
        String str = "Health";
        g2.drawString(str, DEFAULT_VIEW_SIZE/2-f.stringWidth(str)/2, 30);
        if(p.getHealth() > 30)
        {
        	g2.setColor(Color.green);
        }
        else 
    	{
        	g2.setColor(Color.red);
    	}
        if(p.getHealth() >= 0 )
        {
        	g2.fillRect(DEFAULT_VIEW_SIZE/2-100, 40, p.getHealth()*2,10);
        }
        else
        {
        	showEndMenu(p, g2);	
        }
        
        showNumberOfWeapons(p, g2);
        if(gameOver) 
    	{
        	showEndMenu(p, g2);	
    	}
	}
	
	public void showNumberOfWeapons(Player p, Graphics2D g2)
	{
		if (p.getNumberOfWeapons() > 0) 
		{		
			Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
			g2.setFont(font);
			FontMetrics f = g2.getFontMetrics(font);
	        g2.setColor(Color.black);
	        
	        g2.fillRect(DEFAULT_VIEW_SIZE/4-125, 39, 50*2,12);
	        
	        String str = "Weapons Collected";
	        
	        g2.drawString(str, DEFAULT_VIEW_SIZE/4-f.stringWidth(str), 30);
	        
	        if(p.getNumberOfWeapons() > 3) 
        	{
	        	g2.setColor(Color.blue);	
        	}
	        else g2.setColor(Color.red);
	        {
	        	g2.fillRect(DEFAULT_VIEW_SIZE/4-125, 40, p.getNumberOfWeapons()*10,10);
	        }
		}
	}
	public void setGameOver(boolean gameover)
	{
		this.gameOver = gameover;
	}
	public void showEndMenu(Player p , Graphics2D g2)
	{
		//paints screen black and adds white text
		int finHealth = p.getHealth();
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
		g2.setFont(font);
		FontMetrics f = g2.getFontMetrics(font);
		g2.setColor(Color.black);
		g2.fillRect(0, 0, DEFAULT_VIEW_SIZE, DEFAULT_VIEW_SIZE);
        g2.setColor(Color.WHITE);
        String str = ("GAME OVER");//\n YOU WALKED\n " + p.getSteps() + "\nWITH " + finHealth + " HEALTH");
        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 200);
        str = ("YOU WALKED\n " + p.getSteps());// + "\nWITH " + finHealth + " HEALTH");
        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 250);
        str = ("\nWITH " + finHealth + " HEALTH");
        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 300);
	}
}