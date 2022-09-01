/**part of the game menu allows player to buy a tower for defense 
 * 
 */
package game;

import java.awt.Graphics;

public class MenuTower implements Animatable 
{
	private GameState state;
	private int x, y;
	private int tower;
	public MenuTower (GameState state, int x, int y, int tower)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		this.tower = tower;
	}
	
	public void update(double timeElapsed) 
	{
		
		if (state.getMouseX() >= x && state.getMouseX() < x + 50 && state.getMouseY() >= y && state.getMouseY() < (y + 50) && state.isMouseClicked() && state.hasStart)
		{
			if(tower == 1 && state.evolution >= 200)
			{
			state.evolution = state.evolution - 200;
			state.addGameObject(new MouseTower(state, tower));
			}
			if(tower == 2 && state.medication >= 50)
			{
			state.medication = state.medication - 50;
			state.addGameObject(new MouseTower(state, tower));
			}
			if(tower == 3 && state.medication >= 50)
			{
			state.medication = state.medication - 50;
			state.addGameObject(new MouseTower(state, tower));
			}
		}
	}

	public void draw(Graphics g) 
	{
		if(tower == 1)
		g.drawImage(ResourceLoader.getLoader().getImage("towerW.png"), x , y, 50, 50 , null);
		if(tower == 2)
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(1).png"), x + 10, y, 25, 50 , null);
		if(tower == 3)
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(2).png"), x, y, 47, 47 , null);
//		g.setColor(Color.BLUE);
//		g.fillRect(x,y,50,50);

	}

}
