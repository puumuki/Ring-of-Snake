package fi.ringofsnake.util;

import org.newdawn.slick.geom.Vector2f;

import fi.ringofsnake.entities.AEntity;

public class Impulse {
 
	private static final float STEP = 0.001f;
	
	private float time;
	
	private Vector2f force;
	
	private AEntity target;
	
	public Impulse( float time, Vector2f force, AEntity target ) {
		this.time = time;
		this.force = force;
		this.target = target;
	}
	
	public void launch( float time, Vector2f force ) {
		this.time = time;
		this.force = force;
	}
	 
	public boolean isAffecting() {
		return time > 0;
	}
	
	public void update( long delta ) {
		time -= STEP * delta;
		
		if( time > 0 ) {
			target.velocity.x += force.x;
			target.velocity.y += force.y;
		}
	}
	
	public void add(Vector2f v) {
		force.add(v);
	}
}
