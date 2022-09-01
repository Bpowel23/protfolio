/**builds a "path" out of sent x, y values
 * Benjamin Powell u1379684
 * 10-31-21
 */

package game;

import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Path 
{
	
	private Point[] pointsInLine;
	
	public Path (Scanner path)
	{
		
		pointsInLine = new Point[path.nextInt()];
		
		
		for(int i = 0 ; i < pointsInLine.length -1 ; i++)
		{
			pointsInLine[i] = new Point(path.nextInt(),path.nextInt());
		
		}
		
	}
	public void draw(Graphics g)
	{
		for(int i = 0; i < pointsInLine.length -2 ;i++)
		{
			
			g.drawLine((int)pointsInLine[i].getX(), (int)pointsInLine[i].getY(), (int)pointsInLine[i+1].getX(), (int)pointsInLine[i+1].getY());
			
		}
	}
	public double getPathLength()
	{
		double PathLength = 0;
		
		for(int i = 0; i < pointsInLine.length-2 ;i++)
		{
			PathLength = PathLength + getSegmentLength(pointsInLine[i],pointsInLine[i+1]);
			
		}
		return PathLength;
	}
	
	
	
	public Point getPathPosition ( double percentTraveled)
	{
		
		Point Where = new Point(236 , 4);
		double distanceTraveled = getPathLength() * percentTraveled;
		double distanceBetween = 0;
		
	
		for(int i = 0; i < pointsInLine.length -1;i++)
		{
			Point zero =  new Point((int)pointsInLine[i].getY(),(int)pointsInLine[i].getX() );
			Point one =   new Point((int)pointsInLine[i+1].getY(),(int)pointsInLine[i+1].getX() );
			
			double segDistance = getSegmentLength(pointsInLine[i],pointsInLine[i+1]);
			if( segDistance > distanceTraveled)
			{
				
				distanceBetween = distanceTraveled/segDistance;
				double X = ((zero.getX()*(1-distanceBetween)) + (one.getX())* distanceBetween);
				double Y = ((zero.getY()*(1-distanceBetween)) + (one.getY())* distanceBetween);
				
				Where = new Point((int)X ,(int)Y) ;
			
				return Where;
				
			}
			else
				distanceTraveled = distanceTraveled - segDistance;
		}
		return Where;
	}
	
	
	
	public double getSegmentLength (Point a, Point b)
	{
		double length = 0;
		double A =  b.getX() - a.getX();
		double B =  b.getY() - a.getY();
		length = Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2));
		
		return length;
	}
	
	public void drawDot(Graphics g)
	{
		for(int i = 0; i < pointsInLine.length -1 ;i++)
		{
			
			Point p = new Point((int)pointsInLine[i].getY(),(int)pointsInLine[i].getX() );
			g.setColor(Color.BLACK);
	    	g.fillOval(p.y-5,p.x-5,10,10);
			
		}
	}
	public boolean notOnPath(Point p)
	{
		
		for(int i = 0; i < pointsInLine.length -1 ;i++)
		{
			Point onP = new Point((int)pointsInLine[i].getY(),(int)pointsInLine[i].getX() );
			if(p.y >= onP.x - 25 && p.y <= onP.x + 25 && p.x >= onP.y -25 && p.x <= onP.y + 25)
				return false;
		}
		
		return true;
	}
	
	
	
}
