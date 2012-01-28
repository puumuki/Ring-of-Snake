package fi.ringofsnake.controllers;
import org.newdawn.slick.ControlledInputReciever;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.Log;

import fi.ringofsnake.entities.Player;




public class JoystickListener implements ControllerListener {

	private Input input;
	
	private Player player;

	public JoystickListener( Player player ) {
		this.player = player;
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {
		
	}

	@Override
	public void controllerLeftPressed(int controller) {			
		Log.info("Controller Left Pressed " + controller + " axis ");	
	}

	@Override
	public void controllerLeftReleased(int controller) {
		Log.info("Controller Left Released " + controller);
		
	}

	@Override
	public void controllerRightPressed(int controller) {
		Log.info("Controller Right Pressed " + controller);
		
	}

	@Override
	public void controllerRightReleased(int controller) {
		Log.info("Right released " + controller);
		
	}

	@Override
	public void controllerUpPressed(int controller) {
		Log.info("Right released " + controller);
		
	}

	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub
		
	}
	
}
