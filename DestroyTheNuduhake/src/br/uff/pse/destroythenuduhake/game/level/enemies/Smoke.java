package br.uff.pse.destroythenuduhake.game.level.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Smoke extends Actor implements Animator.Listener {

	private static final float DURATION = 3f;
	private Animator anim;
	
	public Smoke(float x, float y, GraphicAsset img) {
		super();
		anim = new Animator(8, 8, DURATION, img, this);
		setPosition(x, y);
		setScale(2);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		anim.draw(batch, getX(), getY());
	}
	
	@Override
	public void onEnded() {
		remove();
	}

}
