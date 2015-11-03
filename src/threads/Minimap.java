package threads;

import utils.Utils;
import main.Main;
/**
 * Description:
 * 	This thread will move random through the minimap
 * @author Manulaiko
 * @date   23/04/2014
 */
public class Minimap extends Thread
{	

	/**
	 * Description:
	 * 	run(), nothing to explain :D
	 */
	public void run()
	{
		Main main = new Main();
		try {
			while(true)
			{
				int x1 = (int)main.mTop.getX();
				int y1 = (int)main.mTop.getY();
				int x2 = (int)main.mBottom.getX();
				int y2 = (int)main.mBottom.getY();
				int rX = Utils.random.nextInt(x2 - x1) + x1;
				int rY = Utils.random.nextInt(y2 - y1) + y1;
				
				//System.out.println("Moving \"randomly\" through the minimap"); 
				Utils.click(rX, rY);
				//if(Main.debug)System.out.println("Sleeping (15000)..."); 
				Thread.sleep(Utils.random.nextInt(20000));
			}
		} catch(Exception e) {
			
		}
	}
}
