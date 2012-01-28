package fi.ringofsnake.gamestates;

import java.io.InputStream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import sun.util.logging.resources.logging;

import fi.ringofsnake.main.Main;
import fi.ringofsnake.io.ResourceManager;

public class MainMenuGameState extends BasicGameState {

		private int stateID = -1;
		
		private Image testImage;
		
		
		public MainMenuGameState(int stateID) {
			this.stateID = stateID;
		}
		
		@Override
		public void init(GameContainer container, StateBasedGame game)
				throws SlickException {
			
			try {
				// we load all resources here because we're awesome ;)
				loadResourceFile();
			}
			catch (SlickException e) {
				System.out.println("ERROR: " + e.getMessage());
				System.exit(0);
			}
			
			testImage = ResourceManager.fetchImage("SNAKE_BODY");
		}
		@Override
		public void render(GameContainer container, StateBasedGame game, Graphics g)
				throws SlickException {
			// TODO Auto-generated method stub

			g.drawString("it works!", 10, 10);
			
			g.drawImage(testImage, 50, 50);
			
		}
		@Override
		public void update(GameContainer container, StateBasedGame game, int delta)
				throws SlickException {
			
			Input input = container.getInput();
			
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				//TODO check state to enter
				game.enterState(Main.PLAY_GAME_STATE);
			}

		}
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return stateID;
		}
		
		
		public void loadResourceFile() throws SlickException {
			Log.info("Loading resources.");
			String path = "resources/resources.xml";
			try {
				InputStream stream = getClass().getClassLoader().getResourceAsStream(path);			
				ResourceManager.getInstance().loadResources(stream); 
			} catch (Exception e) {
				e.printStackTrace();
				throw new SlickException("Sorry, I failed to load the resource file at " + path + ": ");
			}		 
		}

}
