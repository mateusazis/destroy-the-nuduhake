package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Smoke extends Animator{

	private static final float DURATION = 3f;
	
	public Smoke(float x, float y, GraphicAsset img) {
		super(8, 8, DURATION, img);
		setPosition(x, y);
		setScale(2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onEnded() {
		super.onEnded();
		remove();
		super.dispose();
	}

}
