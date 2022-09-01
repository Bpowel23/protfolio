/**after player buys a tower this represents that tower to be placed on the play field
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class MouseTower implements Animatable 
{
	private GameState state;
	private int tower;
	private boolean onPath;
	private Path path;
	
	public MouseTower (GameState state, int tower)
	{
		this.state = state;
		this.tower = tower;
		onPath = true;
		
	}
	
	public void update(double timeElapsed) 
	{
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		Point p = new Point(state.getMouseX(), state.getMouseY());
		
		if(state.getMouseX() < 600 && path.notOnPath(p) && state.isMouseClicked())
		{
			state.removeGameObject(this);
			if(tower == 1)
			state.addGameObject(new WhiteTower(state, state.getMouseX()-25, state.getMouseY()-25));
			if(tower == 2)
			state.addGameObject(new CapsleTower(state, state.getMouseX()-25, state.getMouseY()-25));
			if(tower == 3)
			state.addGameObject(new TabletTower(state, state.getMouseX()-25, state.getMouseY()-25));
		}
//		if (state.getMouseX() >= x && state.getMouseX() < x + 50 && state.getMouseY() >= y && state.getMouseY() < (y + 50) && state.isMouseClicked())
//		{
//			
//			state.addGameObject(new MouseTower(state);
//		}
		
	}

	public void draw(Graphics g) 
	{
		if(tower == 1)
		g.drawImage(ResourceLoader.getLoader().getImage("towerW.png"), state.getMouseX()-25,state.getMouseY()-25, 50, 50 , null);
		if(tower == 2)
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(1).png"), state.getMouseX()-20,state.getMouseY()-25, 25, 50 , null);
		if(tower == 3)
		g.drawImage(ResourceLoader.getLoader().getImage("towerPill(2).png"), state.getMouseX()-25,state.getMouseY()-25, 50, 50 , null);
	}
	public void notOnPath()
	{
		double pathpoint = 0.0;
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		Point p = path.getPathPosition(pathpoint);
		
		
		for(int i = 0; i < 100 ;i++)
		{
			pathpoint = pathpoint + 0.01;
			p = path.getPathPosition(pathpoint);
			if( state.getMouseY() >= p.y - 15 && state.getMouseY() <= p.y -15 )
			onPath = false;
			
		}
		
	}

}
