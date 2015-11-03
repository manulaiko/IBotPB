package utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import main.Main;

/**
 * Description:
 * 	This class will contain methods like pixelSearch() or shitz like that
 * 
 * @author Manulaiko
 * @date   21/04/2014
 */
public class Utils
{
	public static Main main;
	public static Point centerScreen;
	public static Random random = new Random();
	public static Scanner in = new Scanner(System.in);
	/**
	 * Description:
	 * 	Search for a pixel in the screen
	 * 
	 * @param hexColor: the pixel in HEX
	 * @param topLeft: First coordinate
	 * @param bottomRight: Last coordinate
	 * @return the Point object with the value location of the pixel
	 * @throws AWTException: pixel couldn't be found
	 */
	public static Point pixelSearch(String hexColor, Point topLeft, Point bottomRight) throws AWTException{
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	
		int areaWidth = bottomRight.y - topLeft.y;
		int areaHeight = bottomRight.y - topLeft.y;

		Rectangle area = new Rectangle(topLeft.x, topLeft.y, areaWidth, areaHeight);
		
		Color colorColor = Color.decode(hexColor);

		int searchColor = colorColor.getRGB();

		Robot r = new Robot();
		Rectangle screenRect = area;
		BufferedImage bimage = r.createScreenCapture(screenRect);
		int width = bimage.getWidth();
		int height = bimage.getHeight();
		int[] colors = new int[width * height];
		int[] all = bimage.getRGB(0, 0, width, height, colors , 0, width);

		int o = 1;//random.nextInt() + 1;
		Vector<Point> matches = new Vector<Point>();
		for(int i = 0; i < all.length; i += o)
		{
			if (all[i] == searchColor)
			{
				int y = ((i + 1) / width);
				int x = (i) - (y * width);
				Point point = new Point(screenWidth - (screenWidth - topLeft.x) + x,screenHeight - (screenHeight - topLeft.y) + y);
				//if(Main.debug)System.out.println("Match found!\n X: "+ point.x +" Y: "+ point.y); 
				matches.add(point);
			}
		}

		if(matches.size() == 1) {
			return matches.firstElement();
		} else if(matches.size() > 1) {
			return getClosestMatch(matches);
		}
		
		return null;
	}
	
	/**
	 * Description:
	 * 	Performs a mouse click :D
	 * @param x: nothing to explain
	 * @param y: nothing to explain
	 * @throws AWTException: Robots exception
	 */
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	/**
	 * Description:
	 * 	Returns the maximum distance between 2 points
	 * 
	 * @param p1: point 1
	 * @param p2: point 2
	 * @returns distance
	 */
	public static double distMax(Point p1, Point p2){
        return Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2));
    }
	
	public static Point getClosestMatch(Vector<Point> puntos)
	{
		Point p1 = new Point(puntos.firstElement());
        double min = distMax(main.top, centerScreen);
        double dist;

        //if(Main.debug)System.out.println("Calculating closest match...");
        for (int i = 0; i<puntos.size()-1;i++){
            for(int j = i+1; j <puntos.size(); j++){
                dist = distMax((Point)puntos.elementAt(i), centerScreen);
/*                if (min<0){
                    min = dist;
                    p1 = (Point)puntos.elementAt(i);
                    p2 = (Point)puntos.elementAt(j);
                    plano.dibujaLinea(p1,p2,Color.blue);
                    plano.dibujaLinea(p1,p2,Color.yellow);
                    esperar(Integer.parseInt(chRetardo.getSelectedItem()));
                }
                else*/
                if (dist < min){
                    min = dist;
                    p1 = (Point)puntos.elementAt(i);
                }
            }
        }
        //if(Main.debug)System.out.println("Closest match: X: "+ p1.x +" Y: "+ p1.y); 
        return p1;
    }
	
	/**
	 * Description:
	 * 	Initzialice centerScreen point :P
	 */
	public static void setCenterPoint()
	{
		centerScreen = new Point(main.bottom.x / 2, main.bottom.y / 2);
	}
}
