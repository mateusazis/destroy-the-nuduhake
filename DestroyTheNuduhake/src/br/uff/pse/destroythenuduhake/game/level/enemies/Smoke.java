package br.uff.pse.destroythenuduhake.game.level.enemies;

public class Smoke extends Animator{

	private static final float DURATION = 3f;
	
	public Smoke(float x, float y) {
		super(8, 8, DURATION, "images/smoke1.png");
		setPosition(x, y);
		setScale(2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onEnded() {
		super.onEnded();
		remove();
	}

}
