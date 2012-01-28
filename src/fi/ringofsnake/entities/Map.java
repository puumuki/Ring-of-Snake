package fi.ringofsnake.entities;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.io.ResourceManager;

public class Map implements IGameObject {
	
	private char[][] map;
	private HashMap<Character, Tile> tiles = new HashMap<Character, Tile>();
	
	private static int DEFAULT_MAP_SIZE = 3;

	public Map()
	{
		//Map(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE); //FIXME
		tiles.put(' ', new Tile(ResourceManager.fetchImage("TILE_SPACE")));
	}
	
	public Map(int w, int h)
	{
		map = new char[w][h];	//FIXME matrix order?
	}	

	public Tile getTile(int x, int y) {
		return tiles.get(' ');
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
