package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
<<<<<<< HEAD
import org.newdawn.slick.Input;
=======
import org.newdawn.slick.Image;
>>>>>>> matiasw/master
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

<<<<<<< HEAD
import fi.ringofsnake.main.Main;
=======
import fi.ringofsnake.entities.Map;
import fi.ringofsnake.io.ResourceManager;
>>>>>>> matiasw/master

public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	
	private Image snakeUpper;
	private Image snakeBody;
	private Image snakeLower;
	
	private Map current_map = null;
	
	public PlayGameState(int stateID) {
		this.stateID = stateID;
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		snakeUpper = ResourceManager.fetchImage("SNAKE_BODY_UPPER");
		snakeBody  = ResourceManager.fetchImage("SNAKE_BODY");
		snakeLower = ResourceManager.fetchImage("SNAKE_BODY_LOWER");
		
		current_map = new Map();
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int x = 50;
		int y = 50;
		g.drawImage(snakeUpper, x, y);
		g.drawImage(snakeBody, x, y + snakeUpper.getHeight());
		g.drawImage(snakeLower, x, y + snakeUpper.getHeight() + snakeBody.getHeight());
		
		Image tile = current_map.getTile(0, 0).getImage();
		g.drawImage(tile, x + 100, y);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_PAUSE ) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Main.MAINMENU_GAME_STATE);
		}
	}
	@Override
	public int getID() {
		return stateID;
	}

}
