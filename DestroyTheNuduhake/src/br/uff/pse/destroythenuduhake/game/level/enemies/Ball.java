package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

public class Ball extends Enemy{

	private static final float LIFETIME = 5f, SPEED = 1f;
	private static final int ATACK_POWER = 1;
	private float elapsedLifetime = 0;
	
	private boolean dead = false;
	private boolean firstGroundTouched = false;
	
	private GraphicAsset smokeAsset;
	
	public Ball(float x, float y, GraphicAsset asset, GraphicAsset smokeAsset) {
		super(x, y, asset, 100000);
		this.smokeAsset = smokeAsset;
		setJumpVelocity(6);
		setVelocity(SPEED);
		setMaxMoveVelocity(SPEED);
		setState(State.WALKING);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(dead){
			die2();
			return;
		}
		
		moveLeft();
		if(firstGroundTouched)
			jump();

		elapsedLifetime += delta;
		if(elapsedLifetime >= LIFETIME){
			this.die();
		}
	}
	
	@Override
	public void die(){
		super.die();
		dead = true;
	}
	
	public void die2() {
		Smoke smoke = new Smoke(getX(), getY(), smokeAsset);
		getParent().addActor(smoke);
		remove();
		dispose();
//		removeFromLevel();
//		boolean removed = super.remove();
//		Gdx.app.log("", "removed? " + removed);
	}
	
	@Override
	public void touchGround() {
		super.touchGround();
		this.firstGroundTouched = true;
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
