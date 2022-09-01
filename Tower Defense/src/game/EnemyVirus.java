/**creates a enemy onto the play field
 * 
 */
package game;
import java.awt.Graphics;
import java.awt.Point;

//extended from enemy class (inherited)
public class EnemyVirus extends Enemy
{
	//needs a gamestate object to  reference itself over course of the game
	private GameState state;
	int hitPoints;
	
	public EnemyVirus (double p, GameState state)
	{
		//super needs to be the first thing in order to insure the enemy constructor gets built first. (before this object)
		super(p);
		position = p;
		//this.state means taking the gamestate object passed and using its value though out this class
		this.state = state;
		hitPoints = 1;
	}
	//Implemented from enemy class (which implemented it from anamatible class
	public void update(double timeElapsed) 
	{
		//by using position at + 0.060 the object will be moving at a faster speed
		position = position + 0.060 * timeElapsed;
		
		//when object reached the end of the path it removes itself and player loses a life
		//(Value gameover in gamestate loses one.)
		if (position > .99)
		{
			state.removeGameObject(this);
			state.gameover = state.gameover - 1;
			state.medication += 50;
		}	
		
		if (hitPoints < 1)
			state.removeGameObject(this);
			
	}

	
	public void draw(Graphics g) 
	{
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		Point p = path.getPathPosition(position);
		//draws a the virus picture from resource loader and sets it at y, x then forces picture to be 25,25
		g.drawImage(ResourceLoader.getLoader().getImage("Enemy(1).png"), p.y-10, p.x-10, 25, 25, null);
		
	}
	public void getHp( int damage)
	{
		hitPoints -= damage;
	}
}
