/**creates a visual effect of the tablet tower to help remove enemy's
 * 
 */
package game;
import java.awt.Graphics;
import java.awt.Point;

//extended from enemy class (inherited)
public class EffectTablet implements Animatable
{
	//needs a gamestate object to  reference itself over course of the game
	private GameState state;
	private double x, y; // draw location
	private double age;
	
	public EffectTablet (GameState state, Point origin)
	{
		this.state = state;
	
		x = origin.x;
		y = origin.y;
		age = 0;
		
	}
	//Implemented from enemy class (which implemented it from anamatible class
	public void update(double timeElapsed) 
	{
		age += timeElapsed;
		if(age > .5)
			state.removeGameObject(this);
		
		Point p = new Point((int)x,(int)y);
		Enemy e = state.findNearestEnemy(p);
		
		if (e != null && e.getPosition().distance(p) < 50)
		{
			state.removeGameObject(e);
		}
	}
	

	
	public void draw(Graphics g) 
	{
		//draws a the virus picture from resource loader and sets it at y, x then forces picture to be 25,25
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(2).png"), (int)y-45, (int)x-45, 100, 100, null);
		
	}

}
