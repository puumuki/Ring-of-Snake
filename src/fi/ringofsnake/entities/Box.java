package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;

public class Box extends AEntity {

	private Image boxImg;
	
	public Box(int x, int y, float velX, float velY) {
		boxImg = ResourceManager.fetchImage("BOX").getScaledCopy(2.0f);
		position = new Vector2f(x, y);
		velocity = new Vector2f(velX, velY);
		
		shape = new Rectangle(position.x, 
				  position.y, 
				  boxImg.getWidth(), 
				  boxImg.getHeight() );
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		
		boxImg.draw(position.x, position.y);
		grap.draw(shape);

	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		boxImg.rotate(-0.2f*delta);
		position.add(velocity);
		shape.setLocation(this.position.x, this.position.y);
		
		// TODO: check if off screen and destroy
	}
	
}
