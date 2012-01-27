package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Simple interface with defines a game object. 
 * Game object is class with has render and update method.
 * 
 * @author TeeMuki
 */
public interface IGameObject {
	
	/**
	 * Updates game object
	 *  
	 * @param cont
	 * @param delta
	 */
	public void update(GameContainer cont, int delta) throws SlickException;
	
	/**
	 * Renders game object
	 * 
	 * @param cont
	 * @param g
	 */
	public void render(GameContainer cont, Graphics g) throws SlickException;
}
