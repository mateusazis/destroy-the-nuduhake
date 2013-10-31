package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.*;

public class FixedObject extends LevelObject {

	private Vector2 screenPos, stagePos;
	
	public FixedObject(float screenX, float screenY, GraphicAsset graphic){
		super(0, 0, graphic);
		
		screenPos = new Vector2(screenX, screenY);
		stagePos = new Vector2();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		//fixing this object's position to the camera
		
		Stage parent = getStage();
		Camera c = parent.getCamera();
		
		
		stagePos.set(screenPos);
		stagePos.add(c.position.x, c.position.y);
		
		setPosition(stagePos.x, stagePos.y);
	}
}
