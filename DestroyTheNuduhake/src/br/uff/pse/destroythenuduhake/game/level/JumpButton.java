package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class JumpButton extends InputActor {

	public JumpButton(float screenX, float screenY, AssetBundle b, DefaultController controller){
		super(screenX, screenY, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_JUMP), 
				b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_JUMP_PRESSED), 
				controller);
	}
	
	@Override
	public void setPressed(boolean pressed){
		super.setPressed(pressed);
		DefaultController ctrl = getController();
		if(!pressed)
			ctrl.jumpReleased();
		else
			ctrl.jumpPressed();
	}
}
