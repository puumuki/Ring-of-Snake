package fi.ringofsnake.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import fi.ringofsnake.gamestates.MainMenuGameState;
import fi.ringofsnake.gamestates.OptionsGameState;
import fi.ringofsnake.gamestates.PauseGameState;
import fi.ringofsnake.gamestates.PlayGameState;

public class Main extends StateBasedGame {


    public static final int MAINMENU_GAME_STATE = 0;
    public static final int PLAY_GAME_STATE = 1;
    public static final int OPTIONS_GAME_STATE = 2;
    public static final int PAUSE_GAME_STATE = 3;
    public static final int GAMEOVER_GAME_STATE = 4;
	
	public Main(String name) {
		super(name); 
	}

	/**
	 * Game entry point
	 * @param args possible arguments?
	 */
	public static void main(String[] args) {			        
		try {
			AppGameContainer app = new AppGameContainer(new Main("Ring of Snake"));               
	        app.setShowFPS(false);        
	        app.setDisplayMode(800, 600, false);
	        app.setTargetFrameRate(60);
	        app.start();        
		} catch (Exception e) {
			Log.error(e);
		}
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MainMenuGameState(MAINMENU_GAME_STATE));
        this.addState(new PlayGameState(PLAY_GAME_STATE));
        this.addState(new OptionsGameState(OPTIONS_GAME_STATE));
//        this.addState(new GameOverState(GAMEOVERSTATE));
        this.addState(new PauseGameState(PAUSE_GAME_STATE));
        this.enterState(MAINMENU_GAME_STATE);
	}

}
