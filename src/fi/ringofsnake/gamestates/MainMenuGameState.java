package fi.ringofsnake.gamestates;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;


import fi.ringofsnake.entities.AEntity;

import fi.ringofsnake.main.Main;

import fi.ringofsnake.io.ResourceManager;

import fi.ringofsnake.ui.menu.BasicMenuItem;
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
			
			int xOffset = cont.getWidth() / 3;
			int yOffset = 150;
			
			mainmenu.add("play", new BasicMenuItem(xOffset, yOffset , "Play"));
			//mainmenu.add("options", new BasicMenuItem(xOffset, yOffset + 50, "Options"));
			mainmenu.add("info", new BasicMenuItem(xOffset, yOffset + 100, "Info"));
			mainmenu.add("quit", new BasicMenuItem(xOffset, yOffset + 150, "Quit"));					
			
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
			
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				if( mainmenu.isActiveIndex("play") ) {
					game.enterState(Main.PLAY_GAME_STATE);
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
