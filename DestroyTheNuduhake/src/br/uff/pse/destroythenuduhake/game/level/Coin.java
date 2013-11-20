package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static br.uff.pse.destroythenuduhake.game.assets.AssetDatabase.*;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.level.enemies.Animator;

public class Coin extends StageItem{
	private Animator anim;
	
	public Coin(float x, float y, AssetBundle b) {
		super(x, y, b.<GraphicAsset>getAsset(SPRITE_COIN), b.<SoundAsset>getAsset(SOUND_COIN));
		anim = new Animator(3, 2, 0.5f, b.<GraphicAsset>getAsset(SPRITE_COIN_SHINE));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		anim.draw(batch, getX() + getWidth() / 2f, getY());
	}
	
	@Override
	protected void onCollected(Player p) {
		p.addScore(1);
	}
}
