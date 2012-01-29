package fi.ringofsnake.entities;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;

public class Gore extends AEntity {
	
	private static Random random = new Random(); 
	
	private float scale;
	
	private float rotation;
	
	private final static Image[] resource = {ResourceManager.fetchImage("MEAT_1"), 
											 ResourceManager.fetchImage("MEAT_2"), 
											 ResourceManager.fetchImage("MEAT_3"),
											 ResourceManager.fetchImage("BONE_1"),
											 ResourceManager.fetchImage("BONE_2"),
											 ResourceManager.fetchImage("BONE_3")};	
	
	private Image image;
	
	public Gore(float posX, float posY) {
		this.position.set((float)(posX + Math.random() * 100 - 50), (float)(posY + Math.random() * 100 - 50));
		this.velocity = new Vector2f();
		
		velocityLottery();
		
		int index = random.nextInt( resource.length );		
		image = resource[index].copy();
		
		image.setCenterOfRotation( image.getWidth()/2, image.getHeight()/2);
		
		rotation = random.nextFloat() * 5 - 2.5f;
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		grap.drawImage(image, position.x, position.y);
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
		
		this.image.rotate(rotation);
	}

	private void velocityLottery() {
		this.velocity.x = random.nextFloat() * 5 - 2.5f;
		this.velocity.y = random.nextFloat() * 5 - 2.5f;
	}
}
