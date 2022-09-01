/**creates a object to represent the backdrop
 * 
 */
package game;

import java.awt.Graphics;

public class backdrop implements Animatable 
{
	
	public void update(double timeElapsed) 
	{
		//no need to update
	}

	
	public void draw(Graphics g) 
	{
		//uses resourceloader to get image from resources
		g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"), 0, 0, null);
	}

}
