package fi.ringofsnake.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fi.ringofsnake.main.Main;

public class GameOverGameState extends BasicGameState {
	
	private int stateID = -1;
	private UnicodeFont font;
	
	
	public GameOverGameState(int gameStateId) {
		this.stateID = gameStateId;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		java.awt.Font awtFont = new java.awt.Font("Verdana", java.awt.Font.BOLD, 30);
        font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();       
        java.awt.Color topColor = new java.awt.Color( 0xff0000 );
        java.awt.Color bottomColor = new java.awt.Color( 0xbe0000 );
        
        font.getEffects().add(new GradientEffect(topColor, bottomColor, 1f));
        font.loadGlyphs();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setFont(font);
		String text = "OH NOES\n\n" +
			"U died.\n\n" +
			"You made it so far: x^n metres.\n\n" +
			"\n\nPress enter to continue.";
		g.drawString(text, container.getWidth() / 2 - font.getWidth(text) / 2, container.getHeight() / 2 - font.getHeight(text) / 2);
		
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

	@Override
	public int getID() {
		return stateID;
	}

}
