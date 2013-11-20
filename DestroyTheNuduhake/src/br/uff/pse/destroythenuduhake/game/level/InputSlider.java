package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputSlider extends InputActor {

	private Vector2 touchPos;
	
	public InputSlider(float screenX, float screenY, AssetBundle b, DefaultController controller){
		super(screenX, screenY, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_SLIDER), 
				b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_SLIDER_PRESSED), 
				controller);
		
		touchPos = new Vector2();
	}
	
	@Override
	public void setPressed(boolean pressed){
		super.setPressed(pressed);
		if(!pressed){
			DefaultController ctrl = getController();
			ctrl.leftReleased();
			ctrl.rightReleased();
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		Stage parent = getStage();
		
		if(isPressed()){
			DefaultController ctrl = getController();
			
			touchPos.set(Gdx.input.getX(), Gdx.input.getY());
			touchPos = parent.screenToStageCoordinates(touchPos);
			float x = touchPos.x;
			if(x > getX() + getWidth() * getScaleX() / 2f)
				ctrl.rightPressed();
			else
				ctrl.leftPressed();
		}
	}
}
