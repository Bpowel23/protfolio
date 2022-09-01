/**object that will keep track of if the game has started and allow the player to start the game.
 * 
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class gameStart implements Animatable 
{
	private GameState state;
	private int x, y;
	public gameStart (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
	}
	
	public void update(double timeElapsed) 
	{
		if(  state.getMouseX() >= x && state.getMouseX() < x + 150 && state.getMouseY() >= y && state.getMouseY() < (y + 75) && state.isMouseClicked() )
		{
			state.removeGameObject(this);
			state.hasStart = true;
		}
	
	}

	public void draw(Graphics g) 
	{
		
		g.setColor(Color.BLACK);
		g.fillRect(x,y,150,75);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString(" Start ", 235, 275);
	}

}
