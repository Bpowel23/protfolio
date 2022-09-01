/**creates a tower for player defense
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TabletTower implements Animatable 
{
	private GameState state;
	private int x, y;
	private double decay;
	
	public TabletTower (GameState state, int x, int y)
	{
		this.state = state;
		this.x = x;
		this.y = y;
		decay = 0.0;
	}
	
	public void update(double timeElapsed) 
	{
		decay += timeElapsed;
		if(decay > 1.0)
			state.removeGameObject(this);
		
		//find nearest enemy.
		Point towerPoint = new Point(y+19,x+19);
		
		Enemy e = state.findNearestEnemy(towerPoint);
		
		if(e == null)
			return; //nothing found
		
		if(towerPoint.distance(e.getPosition()) > 100)
			return; // Enemy too far away.
		
		//fire away
		
		EffectTablet s = new EffectTablet(state, towerPoint);
		state.addGameObject(s);

		
	}

	public void draw(Graphics g) 
	{
		
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(2).png"), x, y, 50, 50 , null);
	}

}
