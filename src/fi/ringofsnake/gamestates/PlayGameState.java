package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fi.ringofsnake.main.Main;
import fi.ringofsnake.entities.SnakeMap;
import fi.ringofsnake.entities.Tile;
import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.entities.Player;

public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	private Player player;

	private SnakeMap currentMap = null;
	
	private float[] offset = {0.0f, 0.0f};

	public PlayGameState(int stateID) {
		this.stateID = stateID;
	}

	public int getID() {
		return stateID;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)			
			throws SlickException 
	{
		player = new Player(container);
		
		currentMap = new SnakeMap();
		player = new Player(container);		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException 
	{
		//TODO read map
		Tile tile = currentMap.getTile(0, 0);
		
		//Background
		for (int i = -1, n = container.getScreenWidth()/tile.getImage().getWidth() + 1; 
			 i < n; 
			 i++) 
		{
			for (int j = -1, m = container.getScreenWidth()/tile.getImage().getWidth() + 1; 
					 j < m; 
					 j++) 
			{ 
				tile.render(g, (int)(i*tile.getImage().getWidth() + offset[0]), 
							   (int)(j*tile.getImage().getHeight() + offset[1]), i%2==1, j%2==1);
			}
		}
		
		// just for now
		player.render(container, g);

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		player.update(container, delta);
		Input input = container.getInput();
		player.update(container, delta);

		if(input.isKeyPressed(Input.KEY_PAUSE ) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Main.MAINMENU_GAME_STATE);
		}
		
		int mod = currentMap.getTile(0, 0).getImage().getHeight();	//assume rect tiles
		//FIXME bg scrolling, do this with camera?
		float step = (float)Math.sin((double)(System.currentTimeMillis())/1000.0);
		offset[0] = ((offset[0]+step)%mod); 
		offset[1] = ((offset[1]+step)%mod);
	}
}
