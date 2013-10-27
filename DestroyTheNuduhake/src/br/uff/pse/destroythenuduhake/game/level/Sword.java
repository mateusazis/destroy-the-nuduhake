package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Sword extends Actor{
	
//	private GraphicAsset swordAsset;
	private TextureRegion r;
	private enum State{IDLE, SWINGING_FRONT, SWINGING_BACK, RESTORE}
	private State state = State.IDLE;
	
	private float elapsed = 0, backAngle = 45, idleAngle = -30, frontAngle = -150;
	private static final float
		SWING_FRONT_DURATION = 0.2f, SWING_BACK_DURATION = 0.1f, RESTORE_DURATION = 0.1f;
	
	
	public Sword(GraphicAsset swordAsset){
		super();
//		this.swordAsset = swordAsset;
		r = new TextureRegion(swordAsset.getTexture());
		setSize(swordAsset.getWidth(), swordAsset.getHeight());
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
//		swordAsset.render(batch, getX(), getY());
//		batch.draw(r, getX(), getY(), swordAsset.getWidth(), swordAsset.getHeight());
//		batch.draw(r, 0, 0);
//		batch.draw(swordAsset.getTexture(), getX(), getY());
		
		float x = getX(),
				y = getY(),
				originX = getWidth()/2,
				originY = 0,
				width = getWidth(),
				height = getHeight(),
				scaleX = 4,
				scaleY = 4,
				rotation = getRotation();
		batch.draw(r, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
//		batch.draw(r, getX(), getY(), 0, 0, getWidth(), getHeight(), 4, 4, 90, true);
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
		public void act(float delta) {
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
				if(elapsed >= RESTORE_DURATION)
					state = State.IDLE;
				else 
					setRotation(lerp(frontAngle, idleAngle, elapsed / RESTORE_DURATION));
				break;
			}
		}
}
