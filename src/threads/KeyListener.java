package threads;

import main.*;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * Description:
 * 	This thread will listen for a key press, to close program :D
 * @author Manulaiko
 *
 */
public class KeyListener extends JFrame implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Main main = new Main();
    private static boolean wPressed = false;
    public static boolean isWPressed() {
        synchronized (KeyListener.class) {
            return wPressed;
        }
    }

    public void run() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (KeyListener.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            main.close();
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            main.close();
                        }
                        break;
                    }
                    return false;
                }
            }
        });
    }
}