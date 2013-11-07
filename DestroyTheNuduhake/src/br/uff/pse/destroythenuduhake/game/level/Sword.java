package br.uff.pse.destroythenuduhake.game.level;

import java.util.HashSet;
import java.util.Set;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Sword is a physics-less LevelObject with a custom rect. It has a back-and-forth swing animation.
 * IAManager is responsible for checking collisions between the Sword and each enemy. In case of collision, 
 * this object is notified. However, the enemy will only be hit if: 1) The sword is in the SWING_FRONT mode and
 * 2) the enemy has not been previously hit during this movement. 
 * @author mateus
 *
 */
public class Sword extends LevelObject{
	
//	private TextureRegion r;
	private enum State{IDLE, SWINGING_FRONT, SWINGING_BACK, RESTORE}
	private State state = State.IDLE;
	
	private float elapsed = 0, backAngle = 45, idleAngle = -30, frontAngle = -150;
	private static final float
		SWING_FRONT_DURATION = 0.2f, SWING_BACK_DURATION = 0.1f, RESTORE_DURATION = 0.1f;
	
	private Set<Enemy> hitEnemies = new HashSet<Enemy>();
	private Player owner;
	
	
	public Sword(Player owner, GraphicAsset swordAsset){
		super(0, 0, swordAsset);
		this.owner = owner;
//		r = new TextureRegion(swordAsset.getTexture());
		setSize(swordAsset.getWidth(), swordAsset.getHeight());
//		setScaleX(4); setScaleY(4);
		
		
		//update the sword rectangle
		//scale up the height and set the width the same as the height
		Rectangle r = getRect();
		r.height = getHeight() * getScaleY();
		r.width = r.height;
	}
	
	@Override
	public void setFlipped(boolean flipped){
		super.setFlipped(flipped);
	}
	
	public void swing(){
		if(state.equals(State.IDLE))
		{
			elapsed = 0;
			state = State.SWINGING_BACK;
		}
	}
	
	private static float lerp(float a, float b, float pctg){
		return a + (b-a) * pctg;
	}
	
	@Override
	public void setRotation(float degrees) {
		float newAngle = isFlipped() ? -degrees : degrees;
		super.setRotation(newAngle);
	}
	
	@Override
		public void act(float delta) {
//		setRotation(45);
			super.act(delta);
			switch(state){
			case IDLE:
				setRotation(idleAngle);
				break;
			case SWINGING_BACK:
				elapsed += delta;
				if(elapsed >= SWING_BACK_DURATION){
					state = State.SWINGING_FRONT;
					elapsed = 0;
				}
				else 
					setRotation(lerp(idleAngle, backAngle, elapsed / SWING_BACK_DURATION));
				break;
			case SWINGING_FRONT:
				elapsed += delta;
				if(elapsed >= SWING_FRONT_DURATION){
					state = State.RESTORE;
					elapsed = 0;
				}
				else 
					setRotation(lerp(backAngle, frontAngle, elapsed / SWING_FRONT_DURATION));
				break;
			case RESTORE:
				elapsed += delta;
				if(elapsed >= RESTORE_DURATION){
					state = State.IDLE;
					hitEnemies.clear();
				}
				else 
					setRotation(lerp(frontAngle, idleAngle, elapsed / RESTORE_DURATION));
				break;
			}
		}
	
	public boolean canHitEnemies(){
		return state.equals(State.SWINGING_FRONT);
	}
	
	public void onOverlap(Enemy e){
		if(!hitEnemies.contains(e)){
			Gdx.app.log("", "enemy attacked!");
			hitEnemies.add(e);
			e.onAtacked(owner.getAtackPower());
		}
	}
}
