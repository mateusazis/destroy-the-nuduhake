package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

public class Ball extends Shot{

	private boolean firstGroundTouched = false;
	private static final float SPEED = 1f;
	private boolean goingLeft = true;
	private SoundAsset explosionSound;
	
	public Ball(float x, float y, float jumpVelocity, GraphicAsset asset, GraphicAsset smokeAsset, SoundAsset explosionSound) {
		super(x, y, asset, smokeAsset);
		setJumpVelocity(jumpVelocity);
		setVelocity(SPEED);
		setMaxMoveVelocity(SPEED);
		setState(State.WALKING);
		this.explosionSound = explosionSound;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(goingLeft)
			moveLeft();
		else
			moveRight();
		if(firstGroundTouched)
			jump();
	}
	
	@Override
	public void touchGround() {
		super.touchGround();
		this.firstGroundTouched = true;
	}
	
	@Override
	public void onAtacked(int atackPower) {
		goingLeft = false;
	}
	
	@Override
	public void onContactStart(LevelObject other) {
		super.onContactStart(other);
		if(other instanceof Enemy){
			Enemy b = (Enemy)other;
			b.onAtacked(1);
			die();
		}
	}
	
	@Override
	public void die(){
		super.die();
		explosionSound.play();
	}
}
