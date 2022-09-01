/**Pill effect when capsle is firing.
 * 
 */
package game;
import java.awt.Graphics;
import java.awt.Point;

//extended from enemy class (inherited)
public class EffectCapsle implements Animatable
{
	//needs a gamestate object to  reference itself over course of the game
	private GameState state;
	private double x, y; // draw location
	private double age; // how long this object lasts (seconds)
	private double dx, dy;
	
	
	public EffectCapsle (GameState state, Point origin, Point dest)
	{
		this.state = state;
		
		age = 0;
		
		x = origin.x;
		y = origin.y;
		dx = dest.x - origin.x;
		dy = dest.y - origin.y;
		
	}
	//Implemented from enemy class (which implemented it from anamatible class
	public void update(double timeElapsed) 
	{
		age += timeElapsed;
		if(age > 1.5) // life over
		{
			state.removeGameObject(this);
			return;
		}
		
		//distance firing based off of time;
		x += (dx + .01) * timeElapsed;
		y += (dy + .01) * timeElapsed;
		
		Point p = new Point((int)x,(int)y);
		Enemy e = state.findNearestEnemy(p);
		
		if (e != null && e.getPosition().distance(p) < 15)
		{
			state.removeGameObject(e);
			state.removeGameObject(this);
		}
	}
	

	
	public void draw(Graphics g) 
	{
		
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(1).png"), (int)y-20, (int)x-20, 25, 25, null);
		
	}

}
