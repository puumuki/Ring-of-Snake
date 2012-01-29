
package fi.ringofsnake.entities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.util.Impulse;

public class Player extends AEntity {	
	
	private static final int GORE_ITEM_COUNT = 500;
	
	private int lives = 1;
		
	private Animation running;
	private Animation jumping;
	
	private List<Gore> gore = new ArrayList<Gore> (); 
	
	private Sound[] voices;
	
	private Impulse jumpImpulse;
	
	private Impulse hitImpulse;
	
	private float maxSpeed = 1.0f;
	
	private Sound catPissed;
	
	// FIXME
	private int floorlevel = 450;
	
	private Vector2f gravity = new Vector2f(0.000f, 0.01f);
	private int width, height;
	
	
	private static final float padScaling = 0.5f;
	private static final float deadZone = 0.05f;
	
	/**
	 * Creates a new player.
	 */
	public Player( GameContainer cont ) {
		position = new Vector2f(200, floorlevel);
		velocity = new Vector2f();
		
		friction = 0.98f;		
			
		jumpImpulse = new Impulse(0, new Vector2f(0.0f, 0.0f), this );
		hitImpulse = new Impulse(0, new Vector2f(0.0f, 0.0f), this );
		
		running = ResourceManager.fetchAnimation("CAT_RUN");
		jumping = ResourceManager.fetchAnimation("CAT_JUMP");

		voices = new Sound[] {ResourceManager.fetchSound("CAT_1"), ResourceManager.fetchSound("CAT_2"),
				ResourceManager.fetchSound("CAT_3"), ResourceManager.fetchSound("CAT_4")};
		
		width = running.getWidth();
		height = running.getHeight();
		
		shape = new Rectangle(position.x, 
							  position.y + 20, 
							  jumping.getWidth(), 
							  jumping.getHeight() -20 );
		
		Input.disableControllers();
		
		catPissed = ResourceManager.fetchSound("CAT_PISSED");
		
		
		
		for( int i=0; i < GORE_ITEM_COUNT; i++ ) {
			Gore gore = new Gore(position.x, position.y);
			this.gore.add( gore );
		}
	}
	
	/**
	 * Renders the player etc.
	 * @param cont game container
	 * @param grap graphics object
	 */
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		if( isAlive() ) {
			renderLive(grap);
		} else {
			renderDead(cont, grap);	
		}			
	}
	
	private void renderDead( GameContainer cont, Graphics grap) throws SlickException {
		for( Gore g : gore) {
			g.render(cont, grap);
		}
	}
	
	private void renderLive(Graphics grap) {
		if(isJumping())
			grap.drawAnimation(jumping, position.x, position.y);
		else
			grap.drawAnimation(running, position.x, position.y);
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
		updateHitbox();
		playSounds();
		
		if( isAlive() ) {
			for( Gore g : gore ) {
				g.setPos(this.shape.getCenterX(), 
						 this.shape.getCenterY());
			}
		} 
		else {
			for( Gore g : gore ) {				
				g.update(cont, delta);
			}	
		}
	}

	public void playSounds() {
		boolean playing = false;
		
		for (Sound voice : voices) {
			if (voice.playing())
				playing = true;
		}
		if (!playing && Math.random() > 0.995) {
			int whichToPlay = (int)(Math.random() * voices.length);
			voices[whichToPlay].play();
		}
	}

	private void updateHitbox() {
		shape.setLocation(this.position.x, this.position.y);		
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
		if ( input.isKeyDown(Input.KEY_RIGHT) || input.isControllerRight(Input.ANY_CONTROLLER) ) {
			if(velocity.x < maxSpeed)
				x = 0.01f;
		}
		
		if ( (input.isKeyPressed(Input.KEY_UP) || input.isButton1Pressed(Input.ANY_CONTROLLER)) && touchingLand() ) {
			jumpImpulse.launch(0.2f, new Vector2f(0,-0.12f));
		}
				
		velocity.x += gravity.x;
		velocity.y += gravity.y;
		
		velocity.x += x;
		velocity.y += y;
		
		velocity.x *= friction;
		velocity.y *= friction;

		position.x += velocity.x * delta;
				
		position.y += velocity.y * delta;
		
		if( touchingLand() ) {
			position.y = floorlevel;
		}
		
		jumpImpulse.update(delta);
		hitImpulse.update(delta);
		
		running.setSpeed( Math.abs(velocity.length() / 10 * delta) );
		
	}
	
	private boolean touchingLand() {
		return position.y >= floorlevel;
	}
	
	private boolean isJumping() {
		return !touchingLand();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public void boxHit() {
		hitImpulse.launch(0.2f, new Vector2f(-0.05f, 0));
	}
	
	public void removeLive() {
		if( isAlive() ){
			this.lives--;
			
			if( catPissed.playing() == false ) {
				catPissed.play();	
			}
		}		
	}
	
	public boolean isAlive() {
		return this.lives > 0;
	}
}