package fi.ringofsnake.gamestates;

import java.awt.Point;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.Log;

import fi.ringofsnake.entities.AEntity;
import fi.ringofsnake.main.Main;

import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.ui.menu.ImageMenuItem;
import fi.ringofsnake.ui.menu.Menu;

public class MainMenuGameState extends BasicGameState {

		private int stateID = -1;
		
		private List<AEntity> entities = new ArrayList<AEntity>();
		private Menu mainmenu;
		
		private Image menuBg;
		
		public MainMenuGameState(int stateID) {
			this.stateID = stateID;
		}
		
		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {				
			loadResourceFile();			
			initMainMenu( container );
			
			menuBg = ResourceManager.fetchImage("MENU_BG");
		}
		
		private void initMainMenu( GameContainer cont ) throws SlickException {
			mainmenu = new Menu();
			
			int xOffset = cont.getWidth() / 3 + 100;
			int yOffset = cont.getHeight() / 3 + 40;
			
			ImageMenuItem playItem = new ImageMenuItem( new Point(xOffset, yOffset), 
														ResourceManager.fetchImage("MENU_BUTTON_PLAY"));
			
			ImageMenuItem infoItem = new ImageMenuItem( new Point(xOffset, yOffset + 120), 
														ResourceManager.fetchImage("MENU_BUTTON_IFNO"));
						
			ImageMenuItem quitItem = new ImageMenuItem( new Point(xOffset, yOffset + 210), 
														ResourceManager.fetchImage("MENU_BUTTON_QUIT"));

			mainmenu.add("play", playItem);
			mainmenu.add("info", infoItem);
			mainmenu.add("quit", quitItem);					
			
			entities.add(mainmenu);
		}
		
		@Override
		public void render(GameContainer container, 
						  StateBasedGame game, 
						  Graphics g) throws SlickException {
			
			menuBg.draw();
			
			for( AEntity entity : entities ) {
				entity.render(container, g);
			}
		}
		
		@Override

		public void update(GameContainer container, 
						   StateBasedGame game, 
						   int delta)	throws SlickException {

			for (AEntity entity : entities) {
				entity.update(container, delta);
			}			

			Input input =container.getInput();
			
			FadeInTransition in = new FadeInTransition(Color.white);
			FadeOutTransition out = new FadeOutTransition(Color.white);
			
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				if( mainmenu.isActiveIndex("play") ) {
					game.enterState(Main.PLAY_GAME_STATE, in, out);
				}							
				if( mainmenu.isActiveIndex("info")) {
					game.enterState(Main.INFO_GAME_STATE, in, out);
				}				
				else if( mainmenu.isActiveIndex("quit") ) {
					container.exit();
				}
			}
		}
		
		@Override
		public int getID() {		
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
