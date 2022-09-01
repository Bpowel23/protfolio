/**helper class that stored all images into a map  for easy access
 * 
 */
package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ResourceLoader 
{
	private HashMap<String,BufferedImage> map;
	static private ResourceLoader instance;
	
	
	private ResourceLoader()
	{
		map = new HashMap< String, BufferedImage>();
		//HashMap<String, Path> pathMap = new HashMap<String, Path>();
	}
	
	public BufferedImage getImage(String filename) 
	{
		BufferedImage image = null;
		
		if(map.containsKey(filename))
		{
			return map.get(filename);
		}
		else
		try
		{
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream is = loader.getResourceAsStream("resources/" + filename);
			image = javax.imageio.ImageIO.read(is);

//    	Scanner pathScanner = new Scanner(loader.getResourceAsStream("resources/path.txt"));
//    	path = new Path(pathScanner);
			// System.out.println(pathScanner.next());
		} catch (IOException e) 
		{
			System.out.println("Could not load the backdrop or path.");
			System.exit(0);
		}
		map.put(filename, image);
		
//		System.out.println( "image works");
		return image;
	}
	public Path getPath(String filename)
	{
		Scanner pathScanner = null;
		
		ClassLoader pathLoader = this.getClass().getClassLoader();
    	pathScanner = new Scanner(pathLoader.getResourceAsStream("resources/" + filename));
    	Path path = new Path(pathScanner);
		
		
		return path;
	}
	 static public ResourceLoader getLoader()
     {
         if (instance == null)
           instance = new ResourceLoader ();

         return instance;
     }

}
