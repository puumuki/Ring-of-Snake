package fi.ringofsnake.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;

public class Squirrel extends AEntity {

	private Animation running;
	
	public Squirrel(int x, int y, float speed, boolean alt) {
		
		position = new Vector2f(x, y);
		velocity = new Vector2f();
		
		if(alt)
			running = ResourceManager.fetchAnimation("SQUIRREL_RUN2");
		else
			running = ResourceManager.fetchAnimation("SQUIRREL_RUN");
		
		running.setSpeed(speed);
		
		this.shape = new Rectangle(x, y, running.getWidth(), running.getHeight());
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {		
		grap.drawAnimation(running, position.x, position.y);
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		this.shape.setLocation(position.x, position.y);
	}

}
