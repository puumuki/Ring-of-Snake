package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import fi.ringofsnake.main.Main;
import fi.ringofsnake.controllers.JoystickListener;
import fi.ringofsnake.entities.SnakeMap;
import fi.ringofsnake.entities.Tile;
import fi.ringofsnake.io.ResourceManager;

import fi.ringofsnake.entities.Player;


public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	

	private Image snakeUpper;
	private Image snakeBody;
	private Image snakeLower;
	
	private SnakeMap currentMap = null;

	private Player player;

	
	public PlayGameState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		snakeUpper = ResourceManager.fetchImage("SNAKE_BODY_UPPER");
		snakeBody  = ResourceManager.fetchImage("SNAKE_BODY");
		snakeLower = ResourceManager.fetchImage("SNAKE_BODY_LOWER");
		

		currentMap = new SnakeMap();

		
		player = new Player(container);
		
	
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		int x = 50;
		int y = 50;
		
		g.drawImage(snakeUpper, x, y);
		g.drawImage(snakeBody, x, y + snakeUpper.getHeight());
		g.drawImage(snakeLower, x, y + snakeUpper.getHeight() + snakeBody.getHeight());
		
		Tile tile = currentMap.getTile(0, 0);
		
		if( tile != null ) {
			g.drawImage(tile.getImage(), x + 100, y);
		}
		
		// just for now
		g.fillRect(0, 30, 800, 500);
		player.render(container, g);

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
		
		player.update(container, delta);
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_PAUSE ) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Main.MAINMENU_GAME_STATE);
		}
	}
}
