package fi.ringofsnake.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {

	public static final int TILE_WIDTH = 256;
	public static final int TILE_HEIGHT = 256;
	
	private Image image = null;

	public Tile(Image image) {
		assert (image != null);
		assert (image.getWidth() > 0 && image.getHeight() > 0);

		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void render( Graphics g, int x, int y, boolean flip_x, boolean flip_y) throws SlickException {
		if (flip_x && !flip_y) {
			g.drawImage(image, x, y, image.getWidth(), 0, 0, image.getHeight());
		} else
		if (flip_x && flip_y) {
			g.drawImage(image, x, y, image.getWidth(), image.getHeight(), 0, 0);
		} else
		if (!flip_x && flip_y) {
			g.drawImage(image, x, y, 0, image.getHeight(), image.getWidth(), 0);
		} else
		if (!flip_x && !flip_y) {
			render(g, x, y);
		}
	}

	public void render( Graphics g, int x, int y) throws SlickException {
		g.drawImage(image, x, y);
	}	
}
