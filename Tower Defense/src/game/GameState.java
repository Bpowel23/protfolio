/**Main class of game that keeps track of all objects and passes all references other classes need.
 * 
 */
package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class GameState 
{
	List<Animatable> gameObjects;
	List<Animatable> objectsToRemove;
	List<Animatable> objectsToAdd;
	
	int mouseX, mouseY;
	boolean mousePressed;
	int gameover = 10;
	int score = 0;
	int evolution = 400;
	int medication = 0;
	boolean hasStart;

	public GameState ()
	{
		
		
		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();
		
		mouseX = mouseY = 0;
		mousePressed = false;
		hasStart = false;
		
	}
	public void addGameObject (Animatable a)
	{
		objectsToAdd.add(a);
	}
	public void removeGameObject (Animatable a)
	{
		objectsToRemove.add(a);
	}
	public void setMouseLocation (int x, int y)
	{
		mouseX = x;
		mouseY = y;
//		System.out.println("click");
	}
	public int getMouseX()
	{
		return mouseX;
	}
	public int getMouseY()
	{
		return mouseY;
	}
	public boolean isMouseClicked()
	{
	
		return mousePressed;
	}
	public void consumeMouseClick()
	{
		mousePressed = false;
	}
	public void setMouseClicked() 
	{
		mousePressed = true;
	}
	public void updateAll(double elapsedTime )
	{
		
		for(Animatable a : gameObjects)
			a.update(elapsedTime);
		
		gameObjects.removeAll(objectsToRemove);
		objectsToRemove.clear();
		
		gameObjects.addAll(objectsToAdd);
		objectsToAdd.clear();
		
	}
	public void drawAll(Graphics g)
	{
		for (Animatable a : gameObjects)
			a.draw(g);
	}
	public int getScore() 
	{
		return score;
	}
	public int getEvolution() 
	{
		return evolution;
	}
	public int getMedication()
	{
		return medication;
	}
	public boolean gameover(Graphics g)
	{
		if(gameover <= 0 )
		{
			g.setColor(Color.BLACK);
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
    		g.drawString("Game Over", 80,300);
			return false;
			
		}
		return true;
	}
	/**
	 * Finds and returns the enemy nearest to the specified point.
	 * 
	 * @return a reference to the enemy nearest the point, or null point
	 */
	public Enemy findNearestEnemy(Point p)
	{
		Enemy closest = null;
		for (Animatable a : gameObjects)
		{
			if(a instanceof Enemy) 
			{
				Enemy e = (Enemy) a;
				if(closest == null)
					closest = e;
				else 
				{
					Point currentClosestPosition = closest.getPosition();
					Point enemyPosition = e.getPosition();
					
					double distanceToCurrentClosest = p.distance(currentClosestPosition);
					//can do etherway
					double distanceToEnemy = p.distance(enemyPosition);
					
					if( distanceToEnemy < distanceToCurrentClosest)
						closest = e;
				}
			}
		}
		
		return closest;
	}

}
