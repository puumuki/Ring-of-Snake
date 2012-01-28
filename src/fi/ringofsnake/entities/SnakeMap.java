package fi.ringofsnake.entities;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class SnakeMap implements IGameObject {
	
	private char[][] map;
	private HashMap<Character, Tile> tiles = new HashMap<Character, Tile>();
	
	private static int DEFAULT_MAP_SIZE = 3;
	
	private HashMap<Character, Tile> createTileSet()
	{
		tiles.put(' ', new Tile(ResourceManager.fetchImage("TILE_SPACE")));
		tiles.put('^', new Tile(ResourceManager.fetchImage("SNAKE_BODY_UPPER")));
		tiles.put('|', new Tile(ResourceManager.fetchImage("SNAKE_BODY")));
		tiles.put('v', new Tile(ResourceManager.fetchImage("SNAKE_BODY_LOWER")));
		return tiles;
	}

	public SnakeMap()
	{
		//Map(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE); //FIXME
		createTileSet();
	}
	
	public SnakeMap(int w, int h)
	{
		map = new char[w][h];
	}	

	public Tile getTile(int x, int y) {
		//TODO
		return tiles.get(' ');
	}
	
	public Tile getTile(char c) {
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
