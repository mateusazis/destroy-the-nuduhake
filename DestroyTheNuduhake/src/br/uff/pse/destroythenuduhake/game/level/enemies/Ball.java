package br.uff.pse.destroythenuduhake.game.level.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

public class Ball extends Enemy{

	private static final float LIFETIME = 2f;
	private static final int ATACK_POWER = 1;
	private float elapsedLifetime = 0;
	
	private boolean dead = false;
	
	public Ball(float x, float y, GraphicAsset asset) {
		super(x, y, asset, 100000);
		setJumpVelocity(6);
		setVelocity(1f);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(dead){
			die2();
			return;
		}
		
		moveLeft();
		jump();
		elapsedLifetime += delta;
		if(elapsedLifetime >= LIFETIME){
			this.die();
		}
	}
	
	@Override
	public void die(){
		dead = true;
	}
	
	public void die2() {
		
		Gdx.app.log("", "died");
		Smoke smoke = new Smoke(getX(), getY());
		getParent().addActor(smoke);
		remove();
		dispose();
//		removeFromLevel();
//		boolean removed = super.remove();
//		Gdx.app.log("", "removed? " + removed);
	}
	
	@Override
	public void onContactStart(LevelObject other) {
		boolean collisionWithPlayer = other == getTarget();
		if(collisionWithPlayer){
			getTarget().onAtacked(ATACK_POWER);
			this.die();
		}
	}

	
	
}
