package fi.ringofsnake.entities;



import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import fi.ringofsnake.io.ResourceManager;

public class ScrollingBackGround implements IGameObject {

	
	private List<Point> backgroundImagePositions = new ArrayList<Point>();
	
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
		
		if( cont.getInput().isKeyPressed(Input.KEY_R)) {
			horizontalOffset = 0;
		}
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		
		int horizontalPos = cont.getHeight() - background.getHeight()+100;
		
		g.drawImage(background,  (int)  - horizontalOffset, horizontalPos );		
		g.drawImage(background,  (int)  - horizontalOffset + background.getWidth(), horizontalPos );
		g.drawImage(background,  (int)  - horizontalOffset + background.getWidth()*2, horizontalPos );
		
		System.out.println( horizontalOffset );
		
		if(  background.getWidth() * 2 < horizontalOffset ) {
			horizontalOffset = 0;
		}
	}
}
