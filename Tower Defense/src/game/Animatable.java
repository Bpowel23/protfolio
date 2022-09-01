/**Class that makes sure all "animatable" objects that implements it has an update and draw function
 * creates objects that can me animated.
 */
package game;

import java.awt.Graphics;

public interface Animatable 
{	
	//allows object to update getting a new position
	public void update(double timeElapsed);
	//allows object to draw itself allowing for changes within the object
	public void draw  (Graphics g);
}
