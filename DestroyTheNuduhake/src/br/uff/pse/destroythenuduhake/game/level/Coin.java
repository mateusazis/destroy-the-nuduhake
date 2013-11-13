package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.enemies.Animator;

public class Coin extends LevelObject{

	
	private static final float SINE_DURATION = 3f, SINE_MAGNITUDE = 20f;
	
	private float elapsed = 0, y0;
	private Animator anim;
	
	
	public Coin(float x, float y, AssetBundle b) {
		super(x, y, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_COIN));
		anim = new Animator(3, 2, 0.5f, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_COIN_SHINE));
		this.y0 = y;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		anim.draw(batch, getX() + getWidth() / 2f, getY());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsed += delta;
		if(elapsed >= SINE_DURATION)
			elapsed -= SINE_DURATION;
		setY(y0 + SINE_MAGNITUDE * (float)Math.sin(Math.PI * 2 * elapsed / SINE_DURATION));
	}
	
	@Override
	public void onOverlap(LevelObject other){
		if(other instanceof Player){
			Player p = (Player)other;
			p.addScore(1);
			remove();
		}
	}

}
