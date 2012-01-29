package fi.ringofsnake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.io.ResourceManager;

public class NumberTable extends AEntity {

	private Image[] numbers = {ResourceManager.fetchImage("NUM_0"), 
							   ResourceManager.fetchImage("NUM_1"), 
							   ResourceManager.fetchImage("NUM_2"),
							   ResourceManager.fetchImage("NUM_3"),
							   ResourceManager.fetchImage("NUM_4"),
							   ResourceManager.fetchImage("NUM_5"),
							   ResourceManager.fetchImage("NUM_6"),
							   ResourceManager.fetchImage("NUM_7"),
							   ResourceManager.fetchImage("NUM_8"),
							   ResourceManager.fetchImage("NUM_9"),
							   ResourceManager.fetchImage("NUM_M")};
	
	private static final int NUMBER_COUNT = 6;	
	
	private int number;

	public NumberTable() {
		this.position = new Vector2f();
	}
	
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
				
		String numberString = String.valueOf(number);
		
		for (int i = NUMBER_COUNT; i > 0 ; i-- ) {			
			int index = numberString.length() - 1 -  i;
			
			if( index >= 0 ) {
				
				//TODO: this can be done more efficiently....
				index = Integer.parseInt( Character.toString(numberString.charAt(index))) ;				
			} else {
				index = 0;
			}			
			
			grap.drawImage(numbers[index], position.x - i * 50, position.y );
		}
		
		grap.drawImage(numbers[ numbers.length-1 ], position.x, position.y );
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
	}
	
	public void setNumber( int number ) {
		this.number = number;
	}
}
