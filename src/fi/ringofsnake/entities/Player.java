package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;

public class Player extends AEntity {

	// quick hack, ei näin
	private Image playerImg;
	
	public Player() {
		position = new Vector2f();
		velocity = new Vector2f();

		playerImg = ResourceManager.fetchImage("PLAYER");
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		grap.drawImage(playerImg, position.x, position.y);
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		if(cont.getInput().isKeyDown(Input.KEY_RIGHT))
			if(velocity.x == 0)
				velocity.x = (float)0.05;
			else
				velocity.x += 0.005;
		else
			if(velocity.x > 0)
				velocity.x -= 0.05;
			else
				velocity.x = 0;
		position.x += velocity.x * delta;
	}

}
