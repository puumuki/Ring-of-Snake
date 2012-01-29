package fi.ringofsnake.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

import fi.ringofsnake.io.ResourceManager;

public class SquirrelMob extends AEntity {

	private float horizontaPosition = 0;
	
	private float horizontalSpeed = 0.0001f;
		
	private Squirrel[] squirrels;
	
	private Sound runningSound;
	
	private Sound[] chirps;
	
	private static final int SQUIRREL_COUNT = 20;
	
	public SquirrelMob( GameContainer cont ) {
				
		int maxHorizontalPosition = 50;
		
		int minVerticalPosition = 50;
		int maxVerticalPosition = 500;		
		
		squirrels = new Squirrel[SQUIRREL_COUNT];
		
		for (int i = 0; i < squirrels.length; i++) {
			
			int x = -300;
			int y = 20;
			
			float speed = (float)Math.random() + 0.7f;
			
			if (speed > 1)
				speed = 1.0f;
			
			boolean alt = false;
			
			if(Math.random() > 0.5)
				alt = true;
			
			squirrels[i] = new Squirrel(x + (int)(Math.random() * maxHorizontalPosition), 
										y + (int)(Math.random() * maxVerticalPosition - minVerticalPosition), 
										speed, 
										alt);
			
			
			//this.shape = new Rectangle(x, y, width, height)
		}
		
		runningSound = ResourceManager.fetchSound("SQUIRRELS_RUNNING");
		
		chirps = new Sound[] {ResourceManager.fetchSound("SQUIRREL_CHIRP_1"), ResourceManager.fetchSound("SQUIRREL_CHIRP_2"),
				ResourceManager.fetchSound("SQUIRREL_VOICE_1"), ResourceManager.fetchSound("SQUIRREL_VOICE_2"),
				ResourceManager.fetchSound("SQUIRREL_VOICE_3"), ResourceManager.fetchSound("SQUIRREL_VOICE_4")};
		
		this.shape = new Rectangle(0, 0, findSquirrelsMaxHorizontalPosition(), cont.getHeight());
	}
	
	public float findSquirrelsMaxHorizontalPosition() {
		
		float maxX = 0;
		
		for (Squirrel sq : squirrels) {
			if( sq.shape.getMaxX() >  maxX ) {
				maxX = sq.shape.getMaxX();
			}
		}
		
		return maxX;
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		for (int i = 0; i < squirrels.length; i++) {
			squirrels[i].render(cont, grap);
		}
		
		grap.setColor(Color.orange);
		grap.draw(this.shape);
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		
		if(!runningSound.playing())
			runningSound.loop(1, (float)0.7);

		boolean playing = false;
		
		for (Sound chirp : chirps) {
			if (chirp.playing())
				playing = true;
		}
		
		if (!playing && Math.random() > 0.99) {
			int whichToPlay = (int)(Math.random() * chirps.length);
			chirps[whichToPlay].play();
		}
		
		horizontaPosition += horizontalSpeed * delta;
		
		for (Squirrel sq : squirrels) {
			sq.position.x += horizontaPosition; 			
			sq.update(cont, delta);
		}
		
		
		
		Rectangle hitBox = (Rectangle)shape;
		hitBox.setWidth(findSquirrelsMaxHorizontalPosition());
		
		
	}

	public void stop() {
		runningSound.stop();
	}
}
