package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.math.Rectangle;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.control.Level;

public class EndCapsule extends LevelObject {

	public EndCapsule(Rectangle r, AssetBundle b){
		super(r.x, r.y, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_CAPSULE));
	}
	
	@Override
	public void onOverlap(LevelObject other) {
		((Level)getStage()).getParent().changeLevel(2);
	}
}
