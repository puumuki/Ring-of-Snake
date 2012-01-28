package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InfoGameState extends BasicGameState {
	
	private int stateID = -1;
	
	public InfoGameState(int gameStateId) {
		this.stateID = gameStateId;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
