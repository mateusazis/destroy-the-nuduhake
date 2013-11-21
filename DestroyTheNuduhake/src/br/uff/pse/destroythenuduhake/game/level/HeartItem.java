package br.uff.pse.destroythenuduhake.game.level;

import static br.uff.pse.destroythenuduhake.game.assets.AssetDatabase.*;

import com.badlogic.gdx.graphics.OrthographicCamera;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class HeartItem extends StageItem{
	
	public HeartItem(float x, float y, AssetBundle b, OrthographicCamera c) {
		super(x, y, b.<GraphicAsset>getAsset(SPRITE_IN_GAME_HEART), b.<SoundAsset>getAsset(SOUND_HEART), c);
	}
	
	@Override
	protected void onCollected(Player p) {
		p.addLife(2);
	}
}
