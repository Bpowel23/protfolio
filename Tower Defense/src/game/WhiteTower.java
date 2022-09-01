/**creates a tower for the players defense
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class WhiteTower implements Animatable 
{
	private GameState state;
	private int x, y;
	private double timeSinceLastShot;
	
	public WhiteTower (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		timeSinceLastShot = 0;
	}
	
	public void update(double timeElapsed) 
	{
		//cool down
		
		timeSinceLastShot += timeElapsed;
		if(timeSinceLastShot < .5)
		return; // no, nothing left to do.
		
		//find nearest enemy.
		Point towerPoint = new Point(y+19,x+19);
		
		Enemy e = state.findNearestEnemy(towerPoint);
		
		if(e == null)
			return; //nothing found
		
		if(towerPoint.distance(e.getPosition()) > 100)
			return; // Enemy too far away.
		
		//fire away
		
		EffectVirus s = new EffectVirus(state, towerPoint, e.getPosition());
		state.addGameObject(s);
		
		timeSinceLastShot = 0;
	}

	public void draw(Graphics g) 
	{
		
		g.drawImage(ResourceLoader.getLoader().getImage("towerW.png"), x, y, 50, 50 , null);
	}

}
