package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Ball extends Shot{

	private boolean firstGroundTouched = false;
	private static final float SPEED = 1f;
	
	public Ball(float x, float y, GraphicAsset asset, GraphicAsset smokeAsset) {
		super(x, y, asset, smokeAsset);
		setJumpVelocity(6);
		setVelocity(SPEED);
		setMaxMoveVelocity(SPEED);
		setState(State.WALKING);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		moveLeft();
		if(firstGroundTouched)
			jump();
	}
	
	@Override
	public void touchGround() {
		super.touchGround();
		this.firstGroundTouched = true;
	}
}
