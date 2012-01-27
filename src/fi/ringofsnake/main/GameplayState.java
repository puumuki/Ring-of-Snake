package fi.ringofsnake.main;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.Log;


/**
 * The Gameplay state is the one where the game itself happens.
 *
 */
public class GameplayState extends BasicGameState {

	/**
	 * Unique game state ID
	 */
    private int stateID = -1;
    
    private Music gamemusic = null;
    	
	public GameplayState( int stateID ) {
	       this.stateID = stateID;
	}
	    
	@Override
	public void init(GameContainer cont, StateBasedGame state) throws SlickException {
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)	throws SlickException {
		super.enter(container, game);

	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	
		super.leave(container, game);
	}

	@Override
	public void render(GameContainer cont, StateBasedGame state, Graphics graph)
			throws SlickException {
		
	}

	@Override
	public void update(GameContainer cont, StateBasedGame state, int delta)
			throws SlickException {

		Input input = cont.getInput();
		
		if(input.isKeyPressed(Input.KEY_P) 
			|| input.isKeyPressed(Input.KEY_PAUSE) 
			|| input.isKeyPressed(Input.KEY_ESCAPE)) {			
			pauseGame(cont, state);														
		}
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
}
