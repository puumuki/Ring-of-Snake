package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fi.ringofsnake.entities.Player;

public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	
	private Player player;
	
	public PlayGameState(int stateID) {
		this.stateID = stateID;
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
		player = new Player();
		
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		// just for now
		g.fillRect(0, 30, 800, 500);
		player.render(container, g);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		player.update(container, delta);
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
