package fi.ringofsnake.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import fi.ringofsnake.main.Main;

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
		g.drawString("CREDITS\n\n" +
				"We have made this game in 48 hours at Tampere during the Global Game Jam 2012 so you could have fun. Yeah!\n\n" +
				"Miika Hämynen\nTeemu Puukko\nWaltteri Reunamo\nMatias Wilkman\n\n" +
				"Music: Ruulanko by Sami Juntunen (mutetus)\n\n" +
				"Other credits\n" +
				"http://commons.wikimedia.org/wiki/File:2008-09-15_(14)_Grass,_Gras.JPG by Vera Buhl, CC-BY-SA\n" +
				"http://commons.wikimedia.org/wiki/File:Farm-Fresh_box.png by Fatcow Web Hosting, CC-BY\n" +
				"http://www.freesound.org/people/dobroide/sounds/41180/ by dobroide, CC-BY\n" +
				"\n\nPress enter to continue.", 20, 20);
		
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
