package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuGameState extends BasicGameState {

		private int stateID = -1;
		
		public MainMenuGameState(int stateID) {
			this.stateID = stateID;
		}
		@Override
		public void init(GameContainer container, StateBasedGame game)
				throws SlickException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void render(GameContainer container, StateBasedGame game, Graphics g)
				throws SlickException {
			// TODO Auto-generated method stub

			g.drawString("it works!", 10, 10);
			
		}
		@Override
		public void update(GameContainer container, StateBasedGame game, int delta)
				throws SlickException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return stateID;
		}

}
