package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Base class for all the objects of the game. 
 * So where is the encapsulation? 
 * Class fields are public for performance reasons.
 */
public abstract class AEntity implements Comparable<AEntity>, IGameObject {
	
	protected Shape shape;
	
	/**
	 * Entity position
	 */
	public Vector2f position;
	
	/**
	 * Entity velocity or speed
	 */
	public Vector2f velocity;

	/**
	 * Friction affecting to entity
	 */
	public float friction;
			
	/**
	 * Layer number means a drawing order.
	 * Entities with smallest numbers are drawn first.
	 */
	public int layer;
		
	/**
	 * Is entity alive	
	 */
	public boolean alive = true;
	
	/**
	 * The entity is rendered to the screen when this method is called.
	 * 
	 * @param cont Don't read any input or update physics in this method.
	 * @param grap Use this Graphics object to draw the entity so the drawing order is right.
	 * @throws SlickException if something goes wrong
	 */
	public abstract void render(GameContainer cont, 					    
					   			Graphics grap) 
					    		throws SlickException;
	
	/**
	 * In this method an entity's physics are updated, input from user
	 * are handled and so on. 
	 * 
	 * @param cont the container of the game, can give us the input of the player for example
	 * @param delta 
	 * @throws SlickException if something goes wrong
	 */
	public abstract void update(GameContainer cont, 
								int delta) throws SlickException ;
		
	/**
	 * Init positions with given values, this means starting position and current positions.
	 */
	public void initPosition(int x, int y) {
		setPos(x, y);
	}
	
	public void setPos( int x, int y ) {	
		position.x = x;
		position.y = y;
	}
	
	
	public boolean collaiding( AEntity entity ) {
		
		if( shape != null && entity.shape != null ) {
			return shape.contains(shape);
		}
		
		return false;
	}
	
	@Override
	public int compareTo(AEntity o) {
		
		int compare = 0;
		
		if( o.layer > this.layer )
			compare = 1;
		else if( o.layer < this.layer )
			compare = -1;
		
		return compare;
	}
}
