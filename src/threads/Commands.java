package threads;

import java.awt.Point;

import utils.*;
import main.*;
/**
 * Description:
 * 	Thread for collect BonusBoxes
 * @author Manulaiko
 * @date   24/04/2014
 *
 */
public class Commands extends Thread
{
	Main main = new Main();
	/**
	 * Description:
	 * 	Starts botting
	 */
	public void run()
	{
		try {
			while(true) {
				String command = Utils.in.nextLine();
				switch(command)
				{
					case "stats":
						System.out.println("-----------------------------------------------");
						//Main.stats();
						System.out.println("-----------------------------------------------");
						System.out.println("Started uridium: "+ main.uri);
						System.out.println("Started credits: "+ main.cre);
						System.out.println();
						System.out.println("Current uridium: "+ main.wc.uri);
						System.out.println("Current credits: "+ main.wc.cre);
						System.out.println();
						System.out.println("Earned uridium:  "+ (main.wc.uri - main.uri));
						System.out.println("Earned credits:  "+ (main.wc.cre - main.cre));
						System.out.println();
						System.out.println("Collected bb:    "+ main.bbCollected);
						break;
						
					case "close":
						main.close();
						break;
				}

			}
		} catch (Exception e) {
			System.out.println("Something weird happened...");
			System.out.println(e.getMessage());
		}
	}
}
