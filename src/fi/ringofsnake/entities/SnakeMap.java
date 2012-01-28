package fi.ringofsnake.entities;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class SnakeMap implements IGameObject {
		
	public static enum Tileset { 
		SPACE, 
		SNAKE_LEFT, SNAKE_UPPER, SNAKE_RIGHT, SNAKE_LOWER, SNAKE, 
		CORNER_UL, CORNER_UR, CORNER_LR, CORNER_LL,
		NARROW_CORNER_UL, NARROW_CORNER_UR, NARROW_CORNER_LR, NARROW_CORNER_LL,
		STRAIGHT,
		NARROW,
		STRAIGHT_NARROW_LR, STRAIGHT_NARROW_UL, STRAIGHT_NARROW_RL, STRAIGHT_NARROW_LU
	}
	
	private Tileset[][] map;
	private int width, height;
	
	private static int DEFAULT_MAP_SIZE = 3;
	
	private HashMap<Tileset, Tile> tiles = new HashMap<Tileset, Tile>();
	
	private HashMap<Tileset, Tile> createTileSet()
	{
		tiles.put(Tileset.SPACE, new Tile(ResourceManager.fetchImage("TILE_SPACE")));
		tiles.put(Tileset.STRAIGHT, new Tile(ResourceManager.fetchImage("STRAIGHT")));
		//tiles.put(Tileset.STRAIGHT_NARROW_LR, new Tile(ResourceManager.fetchImage("STRAIGHT")));	//TODO
		tiles.put(Tileset.CORNER_UL, new Tile(ResourceManager.fetchImage("CORNER"), true, true));
		tiles.put(Tileset.CORNER_UR, new Tile(ResourceManager.fetchImage("CORNER"), false, true));
		tiles.put(Tileset.CORNER_LR, new Tile(ResourceManager.fetchImage("CORNER"), false, false));
		tiles.put(Tileset.CORNER_LL, new Tile(ResourceManager.fetchImage("CORNER"), true, false));
		return tiles;
	}
		
	public SnakeMap(int w, int h)
	{
		width = w; height = h;
		map = new Tileset[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (j == 0)
				{
					if (i == 0)
						map[i][j] = Tileset.CORNER_UL;
					else if (i == w - 1)
						map[i][j] = Tileset.CORNER_UR;
					else
						map[i][j] = Tileset.STRAIGHT;	//should be a tile with roof
					continue;
				}
				if (i == 0) 
				{
					map[i][j] = Tileset.STRAIGHT;	//TODO rotated 90 deg to get up-down tunnel
					continue;
				}
				if (j == h - 1)
				{
					map[i][j] = Tileset.CORNER_UR;
					continue;
				}
				map[i][j] = Tileset.SPACE;
			}	//end height loop
		}	//end width loop
	}	

	public SnakeMap()
	{
		this(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE); 
		createTileSet();
	}
	
	public Tile getTile(int x, int y) {
		assert(x >= 0 && x < width
			&& y >= 0 && y < height);
		return tiles.get(map[x][y]);
	}
	
	public Tile getTile(Tileset c) {
		return tiles.get(c);
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
