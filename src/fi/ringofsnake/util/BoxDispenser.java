package fi.ringofsnake.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fi.ringofsnake.entities.Box;
import fi.ringofsnake.entities.Player;
import fi.ringofsnake.entities.SquirrelMob;
import fi.ringofsnake.io.ResourceManager;

/**
 * Creates boxes for the player to try to bypass
 * @author Yaamboo
 *
 */
public class BoxDispenser {
	
	private static final int[] BOXLEVELS = {150, 300, 450};
	
	private List<Box> boxes = new LinkedList<Box>();
	
	private Image bangImg;
	private Image bangImg2;
	
	private Player player;
	
	private SquirrelMob squirrels;
	
	private Image boxBit1;
	private Image boxBit2;
	
	public BoxDispenser(Player player, SquirrelMob squirrels) {
		bangImg = ResourceManager.fetchImage("BANG").getScaledCopy(2.0f);
		bangImg2 = ResourceManager.fetchImage("BANG_2").getScaledCopy(2.0f);
		this.player = player;
		this.squirrels = squirrels;

		boxBit1 = ResourceManager.fetchImage("BOX_BIT_1").getScaledCopy(2.0f);
		boxBit2 = ResourceManager.fetchImage("BOX_BIT_2").getScaledCopy(2.0f);
	}
	
	private void createBox() {
		// randomize and stuff
		// let's forget about y-velocity right now
		Box box = new Box(1280 + (int)(Math.random() * 1000), BOXLEVELS[(int)(Math.random()* 3)], (float)(Math.random() * -10) - 5, 0);
		//Log.debug("Creating new box at " + box.position.x + "," + box.position.y);
		boxes.add(box);
	}
	
	public void update(GameContainer cont, int delta) throws SlickException {
		
		if (Math.random() > 0.99)
			createBox();
		
		for (Box box : boxes) {
			box.update(cont, delta);
		}
	}
	
	public void render(GameContainer cont, Graphics g) throws SlickException {
		HashSet<Box> toRemove = new HashSet<Box>();
		for (Iterator<Box> i = boxes.iterator(); i.hasNext();) {
			Box box = i.next();
			if ( box.position.x < -100) {
				//Log.debug("Destroying box at " + box.position.x);
				toRemove.add(box);
			}
			else {

				if (box.colliding(player)) {
					if (box.isVisible()) {
						box.hide(true);
						player.boxHit();
					}
					
					if (box.bangImage() == 0)
						bangImg.draw(box.position.x, box.position.y);
					else
						bangImg2.draw(box.position.x, box.position.y);
				}
				else if (box.colliding(squirrels) && !box.isGoo()) {
					if (box.isVisible()) {
						box.hide(true);
					}
					box.boxbitsdistance++;
					boxBit1.rotate(box.boxbitsdistance / 10);
					boxBit2.rotate(box.boxbitsdistance / -10);
					boxBit1.draw(box.position.x, box.position.y - box.boxbitsdistance);
					boxBit2.draw(box.position.x, box.position.y + box.boxbitsdistance);
				}
				else if (box.isVisible()) {
					box.render(cont, g);
				}
				else
					toRemove.add(box);
			}
		}
		boxes.removeAll(toRemove);
	}
	
}
