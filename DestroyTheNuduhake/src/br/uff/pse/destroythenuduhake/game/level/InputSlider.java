package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class InputSlider extends InputActor {

	private Vector3 touchPos;
	private Camera camera;
	
	public InputSlider(float screenX, float screenY, AssetBundle b, DefaultController controller, Camera c){
		super(screenX, screenY, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_SLIDER), 
				b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INPUT_SLIDER_PRESSED), 
				controller);
		camera = c;
		touchPos = new Vector3();
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
		
		if(isPressed()){
			DefaultController ctrl = getController();
			
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			
			float x = touchPos.x;
			if(x > getX() + (getWidth() / 2f) * getScaleX())
				ctrl.rightPressed();
			else
				ctrl.leftPressed();
			
		}
	}
}
