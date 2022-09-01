/**creates the game menu for the player to interact with
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class gameMenu implements Animatable
{
	private GameState state;
	private boolean objectsAdded;
	int tower = 1;
	
	public gameMenu (GameState state)
	{
		this.state = state;
		this.objectsAdded = false;
		
		
	}
	public void update(double timeElapsed) 
	{
		if (!objectsAdded)
		{
			state.addGameObject(new MenuTower(state,620,130,1));
			state.addGameObject(new MenuTower(state,620,230,2));
			state.addGameObject(new MenuTower(state,690,228,3));
			objectsAdded = true;
		}
	}
	public void draw(Graphics g) 
	{
		//makes game menu
		g.setColor(Color.BLACK);
		g.fillRect(600,0,200,600);
		//makes tower squares
//		g.setColor(Color.BLUE);
//		g.fillRect(620,80,50,50);
//		g.fillRect(690,80,50,50);
		
		//makes life bar
		for(int i = 0; i < state.gameover ;i++)
		{
			int lifeCounterX = 770;
			int lifeLost = i * 15;
			g.setColor(Color.RED);
			g.fillOval(lifeCounterX-lifeLost, 60, 20, 20);
			
			
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Health ", 665, 47);
		g.setColor(Color.WHITE);
		g.drawString("Score " + state.getScore(), 605,20);
		g.drawString("Immune System " + state.getEvolution(), 605,120);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(" 200 ", 625, 195);
		g.setColor(Color.BLUE);
		g.drawString("Medication " + state.getMedication(), 605,220);
		g.drawString(" 50 ", 625, 295);
		g.drawString(" 50 ", 695, 295);
		
	}
	

}
