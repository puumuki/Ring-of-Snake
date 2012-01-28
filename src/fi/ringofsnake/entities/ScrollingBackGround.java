package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class ScrollingBackGround implements IGameObject {
	
	private Image background;
	
	private float horizontalOffset;
	
	private float horizontalStep;
	
	public ScrollingBackGround( float horizontalStep ) {
		background = ResourceManager.fetchImage("GAMEPLAY_BG_BOTTOM");
		this.horizontalStep = horizontalStep;
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {		
		horizontalOffset += horizontalStep * delta; 
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		
		int horizontalPos = cont.getHeight() - background.getHeight()+100;
		
		g.drawImage(background,  (int)  - horizontalOffset, horizontalPos );		
		g.drawImage(background,  (int)  - horizontalOffset + background.getWidth(), horizontalPos );
		g.drawImage(background,  (int)  - horizontalOffset + background.getWidth()*2, horizontalPos );		
		
		if(  background.getWidth() * 2 < horizontalOffset ) {
			horizontalOffset = 0;
		}
	}
}
