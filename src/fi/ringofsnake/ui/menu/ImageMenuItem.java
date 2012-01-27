package fi.ringofsnake.ui.menu;


import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

public class ImageMenuItem implements IMenuItem {

	private int index;
	
	public Image menuImage;
	
	public float scale = 1;
		
	public static final float MAX_SIZE = 0.1f;
	
	private static final Color filterColor = new Color(1f,1f,1f,0.8f);
	
	public boolean isActive = false;
	
	public Point point;
	
	public ImageMenuItem( Point point, Image menuItemImage ) {
		this.point = point;		
		this.menuImage = menuItemImage;
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		if( isActive && scale < (1 + MAX_SIZE) ) {
			scale = (float) (delta * 0.001 + scale);
		}		

		else if( !isActive && scale > (1 - MAX_SIZE)) {
			scale = (float) (scale - delta * 0.001 );
		}
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {		
		if( isActive )
			menuImage.draw(point.x, point.y, scale);
		else {
			menuImage.draw(point.x, point.y, scale, filterColor);
		}
	}

	@Override
	public void setFont(UnicodeFont font) {	
		//Not really need to be implements
	}

	@Override
	public void setText(String text) {
		//Not really need to be implements
	}

	@Override
	public String getText() {
		return "";
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public boolean getActive() {
		return isActive;
	}

	@Override
	public void setActive(boolean isActive) {	
		this.isActive = isActive;		
	}

	@Override
	public Object getValue() {
		return false;
	}
	
	@Override
	public int compareTo(IMenuItem item) {	
		if(item.getIndex() > index ) {
			return 1;
		}
		else if( item.getIndex() < index ) {
			return -1;
		}
		
		return 0;
	}
}
