package fi.ringofsnake.ui.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

public class BasicMenuItem implements IMenuItem {

	/**
	 * Index in menu
	 */
	protected int index;
	
	/**
	 * Menu item text
	 */
	protected String text;
	
	/**
	 * Flag indicates that menu item is currently selected
	 */
	protected boolean isActive;
	
	/**
	 * Font used to render menu item text
	 */
	protected UnicodeFont font;

	/**
	 * This absolute position that is relative to game screen horizontally.
	 */
	public int positionX;
	
	/**
	 * This absolute position that is relative to game screen vertically. 
	 */
	public int positionY;
	
	
	/**
	 * Basic value
	 */
	private Object value = false;
	
	public BasicMenuItem( int posX, int posY, String text ) {
		this.text = text;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {

	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {			
		if( isActive ) {
			font.drawString(positionX, positionY, ">> " +  text);
		}
		else {
			font.drawString(positionX, positionY, text  );
		}
	}

	@Override
	public void setFont(UnicodeFont font) {
		this.font = font;
	}

	@Override
	public void setText(String text) {
		this.text = text;
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
	public String getText() {
		return text;
	}
	
	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public Object getValue() {
		return value;
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
