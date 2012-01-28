package fi.ringofsnake.entities;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import fi.ringofsnake.controllers.JoystickListener;
import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.util.Impulse;

public class Player extends AEntity {

	private Animation running;

	// quick hack, ei näin
	
	private Impulse jumpImpulse;
	
	private float maxSpeed = 1.0f;
	
	// FIXME
	private int floorlevel = 350;
	
	private int width, height;
	
	private Vector2f grafity = new Vector2f(0.000f, 0.01f);
	
	private static final float padScaling = 0.5f;
	private static final float deadZone = 0.05f;
	
	public float cameraOffsetX;
	public float cameraOffsetY;
	
	/**
	 * Creates a new player.
	 */
	public Player( GameContainer cont ) {
		position = new Vector2f(10, floorlevel);
		velocity = new Vector2f();
		
		friction = 0.98f;		
			
		jumpImpulse = new Impulse(0, new Vector2f(0.0f, 0.0f), this );
		
		running = ResourceManager.fetchAnimation("CAT_RUN");
		
		width = running.getWidth();
		height = running.getHeight();
	}
	
	/**
	 * Renders the player etc.
	 * @param cont game container
	 * @param grap graphics object
	 */
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		grap.drawAnimation(running, cameraOffsetX, cameraOffsetY);		
		grap.drawString("vX " + velocity.x, 300, 550);
		grap.drawString("vY " + velocity.y, 300, 570);
		grap.drawString("pX " + position.x, 10, 550);
		grap.drawString("pY " + position.y, 10, 570);
	}

	/**
	 * Updates the player at every tick
	 * @param cont game container
	 * @param delta 
	 */
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {		
		Input input = cont.getInput();		
		updateMovement(input, delta);
	}

	/**
	 * Updates the movement of the player.
	 * @param input mouse, keyboard and controller input wrapper
	 * @param delta
	 */
	private void updateMovement(Input input, int delta) {		
		
		float x = 0;
		float y = 0;

		if ( input.isKeyDown(Input.KEY_LEFT) ) {
			if(velocity.x > -maxSpeed)
				x = -0.01f;

		}
		if ( input.isKeyDown(Input.KEY_RIGHT)) {
			if(velocity.x < maxSpeed)
				x = 0.01f;
		}
		System.out.println( jumpImpulse.isAffecting());
		
		if ( input.isKeyPressed(Input.KEY_UP) && touchingLand() ) {
			jumpImpulse.launch(0.2f, new Vector2f(0,-0.08f));
		}
		/*
		if( input.isKeyDown(Input.KEY_DOWN)) {
			y += 0.1f;
		}
		*/
				
		velocity.x += grafity.x;
		velocity.y += grafity.y;
		
		velocity.x += x;
		velocity.y += y;
		
		velocity.x *= friction;
		velocity.y *= friction;

		position.x += velocity.x * delta;
				
		position.y += velocity.y * delta;
		
		if( touchingLand() ) {
			position.y = 350;
		}
		
		jumpImpulse.update(delta);
		
		running.setSpeed( Math.abs(velocity.length() / 10 * delta) );
	}
	
	private boolean touchingLand() {
		return position.y >= 350;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}