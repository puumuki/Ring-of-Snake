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

import fi.ringofsnake.io.ResourceManager;

public class Player extends AEntity {

	// quick hack, ei näin
	private Image playerImg;
	
	private float maxSpeed = 0.4f;
	
	// FIXME
	private int floorlevel = 350;
	
	private boolean descending = false;
	
	private static final float padScaling = 0.05f;
	private static final float deadZone = 0.05f;
	
	/**
	 * Creates a new player.
	 */
	public Player() {
		position = new Vector2f(10, floorlevel);
		velocity = new Vector2f();
		
		// TODO: shouldn't friction be defined according to the location of the player and not according to his own stats?
		friction = 0.9f;

		playerImg = ResourceManager.fetchImage("PLAYER");
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
		
		float x = input.getAxisValue(3, 1);
		float y = input.getAxisValue(3, 0);

		if (Math.abs(x) > deadZone || Math.abs(y) > deadZone) {
			velocity.x += x * padScaling;
			velocity.y += y * padScaling;
		}

		velocity.x *= friction;
		velocity.y *= friction;
/*
		// This is ugly.
		if (input.isKeyDown(Input.KEY_RIGHT) || input.isControllerRight(Input.ANY_CONTROLLER)) {
			if (velocity.x < maxSpeed)
				velocity.x += 0.005 * friction * delta;
		}
		else if (input.isKeyDown(Input.KEY_LEFT) || input.isControllerLeft(Input.ANY_CONTROLLER)) {
			if (velocity.x > -maxSpeed)
				velocity.x -= 0.005 * friction * delta;
		}
		else {
			if (velocity.x > 0.01)
				velocity.x -= 0.005 * friction * delta;
			else if (velocity.x < -0.01)
				velocity.x += 0.005 * friction * delta;
			else
				velocity.x = 0;
		}
		
		if ((descending == false && (input.isKeyDown(Input.KEY_UP) || input.isButton1Pressed(Input.ANY_CONTROLLER)))) {
			if (canJump()) {
				velocity.y = -0.05f * delta;
			}
			else if(position.y < floorlevel - 150) {
				velocity.y += 0.01 * delta;
				descending = true;
			}
			
		}
		else if (velocity.y != 0) {
			if(position.y >= floorlevel) {
				descending = false;
				velocity.y = 0;
				position.y = floorlevel;
			}
			else
				velocity.y += 0.01 * delta;
		}
*/
	}

	/**
	 * checks if the player can jump from his current position
	 * @return
	 */
	private boolean canJump() {
		if(Math.abs(position.y - floorlevel) < 0.5)
			return true;
		return false;
	}

}
