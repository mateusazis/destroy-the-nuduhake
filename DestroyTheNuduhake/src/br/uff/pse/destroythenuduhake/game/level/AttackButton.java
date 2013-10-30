package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class AttackButton extends InputActor {

	public AttackButton(float screenX, float screenY, AssetBundle b, DefaultController controller){
		super(screenX, screenY, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_ATTACK), 
				b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_ATTACK_PRESSED), 
				controller);
	}
	
	@Override
	public void setPressed(boolean pressed){
		super.setPressed(pressed);
		DefaultController ctrl = getController();
		if(pressed)
			ctrl.firePressed();
		else
			ctrl.fireReleased();
	}
}
