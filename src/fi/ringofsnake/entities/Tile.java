package fi.ringofsnake.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {

	public static final int TILE_WIDTH = 256;
	public static final int TILE_HEIGHT = 256;
	
	private Image image = null;

	public Tile(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void render( Graphics g, int x, int y) throws SlickException {
		g.drawImage(image, x, y);
	}
}
