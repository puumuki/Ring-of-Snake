package fi.ringofsnake.entities;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class ScrollingBackGround implements IGameObject {

	private Image background;
	
	public ScrollingBackGround() {
		background = ResourceManager.fetchImage("GAMEPLAY_BG_BOTTOM");
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		
	}

}
