package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fi.ringofsnake.main.Main;

public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	
	public PlayGameState(int stateID) {
		this.stateID = stateID;
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
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
