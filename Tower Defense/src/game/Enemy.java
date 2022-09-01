/**Class that represents an abstract idea of what a enemy is
 * and what a enemy should have such as a position to move along path.
 */
package game;

import java.awt.Point;

abstract public class Enemy implements Animatable 
{
	//protected only inherited can use.
	//every enemy needs a position
	protected double position;
	private int hitPoints;
	
	//constructor that just sets a value for position
	public Enemy(double p)
	{
		position = p;
	}
	//method that gets a point position on the path using the resource loader
	public Point getPosition ()
	{
		Path path = ResourceLoader.getLoader().getPath("path.txt");
		Point p = path.getPathPosition(position);
		return p;
	}
	public void getHp( int damage)
	{
		hitPoints -= damage;
	}
}
