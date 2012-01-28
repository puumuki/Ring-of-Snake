package fi.ringofsnake.entities;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import fi.ringofsnake.controllers.JoystickListener;
import fi.ringofsnake.io.ResourceManager;

public class Player extends AEntity {

	// quick hack, ei n�in
	private Image playerImg;
	
	private float maxSpeed = 0.4f;
	
	// FIXME
	private int floorlevel = 350;
	
	private boolean descending = false;
	
	private static final float padScaling = 0.05f;
	private static final float deadZone = 0.05f;
	
	JoystickListener listener;
	
	/**
	 * Creates a new player.
	 */
	public Player( GameContainer cont ) {
		position = new Vector2f(10, floorlevel);
		velocity = new Vector2f();
		
		// TODO: shouldn't friction be defined according to the location of the player and not according to his own stats?
		friction = 0.99f;

		playerImg = ResourceManager.fetchImage("PLAYER");
		
		listener = new JoystickListener(this);
		cont.getInput().addControllerListener(listener);
	}
	
	/**
	 * Renders the player etc.
	 * @param cont game container
	 * @param grap graphics object
	 */
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		grap.drawImage(playerImg, position.x, position.y);
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
		
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
	}

	/**
	 * Updates the movement of the player.
	 * @param input mouse, keyboard and controller input wrapper
	 * @param delta
	 */
	private void updateMovement(Input input, int delta) {
				
		float x = 0;
		float y = 0;
		

		if( input.isKeyDown(Input.KEY_LEFT) || input.isControllerLeft(Input.ANY_CONTROLLER)) {
			x = -0.1f;
		}
		if( input.isKeyDown(Input.KEY_RIGHT) || input.isControllerRight(Input.ANY_CONTROLLER)) {
			x = 0.1f;		
		}
		if( input.isKeyDown(Input.KEY_UP) || input.isButton1Pressed(Input.ANY_CONTROLLER)) {
			y -= 0.1f;
		}
		if( input.isKeyDown(Input.KEY_DOWN)) {
			y += 0.1f;
		}
				
		velocity.x += x * padScaling;
		velocity.y += y * padScaling;
		
		velocity.x *= friction;
		velocity.y *= friction;

		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
	}
}
