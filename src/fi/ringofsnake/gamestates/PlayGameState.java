
package fi.ringofsnake.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fi.ringofsnake.main.Main;
import fi.ringofsnake.entities.NumberTable;
import fi.ringofsnake.entities.ScrollingBackGround;
import fi.ringofsnake.entities.SnakeMap;

import fi.ringofsnake.entities.SquirrelMob;

import fi.ringofsnake.io.ResourceManager;
import fi.ringofsnake.entities.Player;

import fi.ringofsnake.util.BoxDispenser;

public class PlayGameState extends BasicGameState {

	private int stateID = -1;
	
	private NumberTable scoreboard;
	
	private Player player;
	private SquirrelMob squirrels;

	private SnakeMap currentMap = null;

	private ScrollingBackGround scrollingBackGround;
	
	private Music gamePlayMusic;
	
	public PlayGameState(int stateID) {
		this.stateID = stateID;		
	}

	public int getID() {
		return stateID;
	}
	
	//Put dispenser here!
	private BoxDispenser boxes;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		
		player = new Player(container);
		currentMap = new SnakeMap(3000, 3, player);
		
		gamePlayMusic = ResourceManager.fetchMusic("GAMEPLAY_BG_MUSIC");

		scrollingBackGround = new ScrollingBackGround(0.5f);
		squirrels = new SquirrelMob(container);
		
		player.position.x = squirrels.findSquirrelsMaxHorizontalPosition() + 10;
		
		boxes = new BoxDispenser(player);
		
		scoreboard = new NumberTable(currentMap);
		scoreboard.setPos( container.getWidth() -100 , container.getHeight() - 100 );
	}


	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		super.enter(container, game);
		gamePlayMusic.loop();	
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {

		super.leave(container, game);
		gamePlayMusic.stop();
		squirrels.stop();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO read map

		scrollingBackGround.render(container, g);
		
		//This moves the map position relative to cat
		g.translate( (int)-(player.position.x + currentMap.tunnelHorizontalOffset), 
						  -container.getHeight()/2 - 80 );
		
		currentMap.render(container, g);		
		g.resetTransform();	
		
		
		squirrels.render(container, g);		
		
		// just for now
		player.render(container, g);


		//Draw scores and other things here. Bitch.		
		//drawDebugLines( container, g );		
		boxes.render(container, g);
		
		scoreboard.render(container, g);
	}

	private void drawDebugLines( GameContainer cont, Graphics g ) {
		
		g.setColor(Color.red);
		
		for( int y = 50; y<cont.getHeight();y += 50) {
			g.drawString( String.valueOf(y), 5, y);
			g.drawLine(20, y, 40, y);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)		throws SlickException {
		
		squirrels.update(container, delta);
		player.update(container, delta);
			
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_PAUSE)	|| input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Main.MAINMENU_GAME_STATE);
		}
				
		scrollingBackGround.update(container, delta);			
		boxes.update(container, delta);				
		currentMap.update(container, delta);
		
		if( player.isAlive() ) {
			scoreboard.update(container, delta);
		}
		
		hitDetection();		
	}
	
	private void hitDetection() {
		if( player.colliding(squirrels) ) {
			player.removeLive();
		}
	}
}