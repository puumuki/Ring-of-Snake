package fi.ringofsnake.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fi.ringofsnake.entities.NumberTable;
import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.main.Main;

public class GameOverGameState extends BasicGameState {
	
	private NumberTable scoreBoard = new NumberTable();
	
	private Image bg;
	
	private int stateID = -1;
	
	public GameOverGameState(int gameStateId) {
		this.stateID = gameStateId;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		bg = ResourceManager.fetchImage("GAMEOVER_BG");		
		scoreBoard.setPos( container.getWidth() / 2 - 100 , container.getHeight() / 2 +100 );
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		bg.draw();
		//scoreBoard.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
		Input input = container.getInput();
		
		FadeOutTransition in = new FadeOutTransition(Color.white);
		FadeInTransition out = new FadeInTransition(Color.white);
		
		if(input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
				game.enterState(Main.MAINMENU_GAME_STATE, in, out);
		}
	}
	
	public void setFinalScores(int scores) {
		scoreBoard.setNumber(scores);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
