/**Class that creates a tanky enemy on the play field
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

//extended from enemy class (inherited)
public class EnemySludge extends Enemy
{
	//needs a gamestate object to  reference itself over course of the game
	private GameState state;
	private int hitPoints;
	private double move;
	
	public EnemySludge (double p, GameState state)
	{
		//super needs to be the first thing in order to insure the enemy constructor gets built first. (before this object)
		super(p);
		position = p;
		//this.state means taking the gamestate object passed and using its value though out this class
		this.state = state;
		hitPoints = 3;
	}
	
	//Implemented from enemy class (which implemented it from anamatible class
	public void update(double timeElapsed) 
	{
		move += timeElapsed;
		//by using position at + 0.030 the object will be moving slower
		position = position + 0.010 * timeElapsed;
		
		if(move < .5)
			position = position + 0.030 * timeElapsed;
		
		//when object reached the end of the path it removes itself and player loses a life
		//(Value gameover in gamestate loses 4.)
		if (position > .99)
		{
			state.removeGameObject(this);
			state.gameover = state.gameover - 4;
		}	
		// plan to add hitpoints so this enemy will be tankyer
		
		if (hitPoints < 1)
			state.removeGameObject(this);
	}

	
	public void draw(Graphics g) 
	{
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		Point p = path.getPathPosition(position);
		
		//draws a the sludge picture from resource loader and sets it at y, x then forces picture to be 25,25
		if(move > .5)
		g.drawImage(ResourceLoader.getLoader().getImage("Enemy(2).png"), p.y-10, p.x-10, 25, 25, null);
		else
		{
			g.drawImage(ResourceLoader.getLoader().getImage("Enemy(2.5).png"), p.y-10, p.x-10, 25, 25, null);
		}
			
		if(move > 1)
			move = 0;
		
	}
	public void getHp( int damage)
	{
		hitPoints -= damage;
	}

}
