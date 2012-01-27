package fi.ringofsnake.ui.menu;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

public class PercentMenuItem extends BasicMenuItem {
	
	private float value = 0;
	
	private float step = 0.0005f;
	
	private float maxValue = 1;
	
	private float minValue = 0;
	
		

	public PercentMenuItem( int x, int y, String text ) {

		super(x, y, text);
		
		java.awt.Font awtFont = new java.awt.Font("Ariel", java.awt.Font.PLAIN, 20);
        font = new UnicodeFont(awtFont);        
	}	


	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		Input input = cont.getInput();
		
		if( isActive ) {
			
			if( input.isKeyDown(Input.KEY_LEFT) && value > minValue) {
				value -= step * delta;								
			}
			
			if( input.isKeyDown(Input.KEY_RIGHT) && value < maxValue ) {
				value += step * delta;
			}
			
			if( value > maxValue ) {
				value = maxValue;
			}
			
			if( value < minValue ) {
				value = minValue;
			}
		}
	}


	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {		
		
		String textValue = text + " " + Math.round(value * 100) + "%";
		
		if( isActive ) {
			font.drawString(positionX, positionY, ">> " +  textValue);
		}
		else {
			font.drawString(positionX, positionY, textValue  );
		}
	}
	

	public Object getValue() {
		return value;
	}
	
	public float getStep() {
		return step;
	}

	public void setStep(float step) {
		this.step = step;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		if( this.maxValue > minValue ) {
			this.maxValue = maxValue;	
		}		
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		if( minValue < maxValue ) {
			this.minValue = minValue;
		}		
	}

	public void setValue(float value) {
		if( value >= minValue && value < maxValue ) {
			this.value = value;
		}		
	}
}
