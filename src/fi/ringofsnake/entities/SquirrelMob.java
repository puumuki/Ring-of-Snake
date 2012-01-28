package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

import fi.ringofsnake.io.ResourceManager;

public class SquirrelMob extends AEntity {

		
	private Squirrel[] squirrels;
	
	private Sound runningSound;
	
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
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		for (int i = 0; i < squirrels.length; i++) {
			squirrels[i].render(cont, grap);
		}

	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		
		if(!runningSound.playing())
			runningSound.loop(1, (float)0.7);

	}

}
