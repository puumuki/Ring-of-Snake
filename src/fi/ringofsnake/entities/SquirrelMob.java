package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;


import fi.ringofsnake.io.ResourceManager;

public class SquirrelMob extends AEntity {

		
	private Squirrel[] squirrels;
	
	private Sound runningSound;
	
	private Sound[] chirps;
	
	private static final int SQUIRREL_COUNT = 40;
	
	public SquirrelMob() {
		
		squirrels = new Squirrel[SQUIRREL_COUNT];
		
		for (int i = 0; i < squirrels.length; i++) {
			
			int x = -100;
			int y = 20;
			
			float speed = (float)Math.random() + 0.7f;
			
			if (speed > 1)
				speed = 1.0f;
			
			boolean alt = false;
			
			if(Math.random() > 0.5)
				alt = true;
			
			squirrels[i] = new Squirrel(x + (int)(Math.random() * 300), y + (int)(Math.random() * 450 - 50), speed, alt);
			
			
			//this.shape = new Rectangle(x, y, width, height)
		}
		
		runningSound = ResourceManager.fetchSound("SQUIRRELS_RUNNING");
		
		chirps = new Sound[] {ResourceManager.fetchSound("SQUIRREL_CHIRP_1"), ResourceManager.fetchSound("SQUIRREL_CHIRP_2"),
				ResourceManager.fetchSound("SQUIRREL_VOICE_1"), ResourceManager.fetchSound("SQUIRREL_VOICE_2"),
				ResourceManager.fetchSound("SQUIRREL_VOICE_3"), ResourceManager.fetchSound("SQUIRREL_VOICE_4")};
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		for (int i = 0; i < squirrels.length; i++) {
			squirrels[i].render(cont, grap);
		}
	}

	@Override
	public boolean colliding(AEntity entity) {		
		for (Squirrel s : squirrels) {
			if( s.colliding(entity) ) {
				return true;
			}				
		}
		
		return false;
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
	}

	public void stop() {
		runningSound.stop();
	}
}
