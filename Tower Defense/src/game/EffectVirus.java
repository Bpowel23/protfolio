/**creates a visual effect for the white tower 
 * 
 */
package game;
import java.awt.Graphics;
import java.awt.Point;

//extended from enemy class (inherited)
public class EffectVirus implements Animatable
{
	//needs a gamestate object to  reference itself over course of the game
	private GameState state;
	private double x, y; // draw location
	private double age; // how long this object lasts (seconds)
	private double dx, dy;
	
	
	public EffectVirus (GameState state, Point origin, Point dest)
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
		x += (dx + .1) * timeElapsed;
		y += (dy + .1) * timeElapsed;
		
		Point p = new Point((int)x,(int)y);
		Enemy e = state.findNearestEnemy(p);
		
		if (e != null && e.getPosition().distance(p) < 10)
		{
			e.getHp(1); 
			state.removeGameObject(this);
			state.evolution += 10;
		}
	}
	

	
	public void draw(Graphics g) 
	{
		//draws a the virus picture from resource loader and sets it at y, x then forces picture to be 25,25
		g.drawImage(ResourceLoader.getLoader().getImage("Enemy(1).png"), (int)y, (int)x, 15, 15, null);
		
	}

}
