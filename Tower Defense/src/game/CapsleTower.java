/**creates a "tower on the play field that fires rapidly
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class CapsleTower implements Animatable 
{
	private GameState state;
	private int x, y;
	private double decay;
	public CapsleTower (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		decay = 0.0;
	}
	
	public void update(double timeElapsed) 
	{
		decay += timeElapsed;
		if(decay > 5.0)
			state.removeGameObject(this);
		
	
		
		//find nearest enemy.
		Point towerPoint = new Point(y+19,x+19);
		
		Enemy e = state.findNearestEnemy(towerPoint);
		
		if(e == null)
			return; //nothing found
		
		if(towerPoint.distance(e.getPosition()) > 100)
			return; // Enemy too far away.
		
		//fire away
		
		EffectCapsle s = new EffectCapsle(state, towerPoint, e.getPosition());
		state.addGameObject(s);
	}

	public void draw(Graphics g) 
	{
		
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(1).png"), x +5, y, 25, 50 , null);
	}

}
