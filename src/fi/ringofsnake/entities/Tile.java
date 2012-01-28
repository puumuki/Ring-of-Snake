package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile implements IGameObject {
	
	private Image image = null;

	public Tile (Image image)
	{
		this.image = image;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
