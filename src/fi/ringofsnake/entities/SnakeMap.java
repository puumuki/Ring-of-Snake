package fi.ringofsnake.entities;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class SnakeMap implements IGameObject {
	
	private int width = 0;
	private int height = 0;
	
	//FIXME: char -> enum ? enum TileType { PIPE, CURVED_PIPE, SUPER_PIPE };
	private char[][] map;
	
	private Map<Character, Tile> tiles = new HashMap<Character, Tile>();
	
	private static int DEFAULT_WIDTH = 3;
	private static int DEFAULT_HEIGHT = 6;

	public SnakeMap()
	{
		map = new char[DEFAULT_WIDTH][DEFAULT_HEIGHT];
		tiles.put(' ', new Tile(ResourceManager.fetchImage("TILE_SPACE")));
	}
	
	public SnakeMap(int w, int h)
	{
		this.width = w;
		this.height = h;
		
		map = new char[w][h];//FIXME matrix order?
	}	

	public Tile getTile(int x, int y) throws SlickException {
		
		if( x <  width && y < height ) {
			return tiles.get(map[x][y]);	
		}
		
		//Is there really need to support higher values? the map has limits and that is it.
		throw new SlickException("Array out off order :(");
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer cont, Graphics graphics) throws SlickException {		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile tile = tiles.get(map[x][y]);
				tile.render( graphics, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT );
			}
		}		
	}

}
