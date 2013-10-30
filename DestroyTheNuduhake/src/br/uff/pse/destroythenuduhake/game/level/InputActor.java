package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.*;

public class InputActor extends LevelObject implements EventListener {

	private GraphicAsset normalTex, pressedTex;
	private boolean isPressed = false;
	private DefaultController ctrl;
	private Vector2 screenPos, stagePos;
	
	public InputActor(float screenX, float screenY, GraphicAsset normalAsset, GraphicAsset pressedAsset, DefaultController controller){
		super(0, 0, normalAsset);
		
		this.ctrl = controller;
		
		normalTex = normalAsset;
		pressedTex = pressedAsset;
		
		screenPos = new Vector2(screenX, screenY);
		
		addListener(this);
		stagePos = new Vector2();
	}

	@Override
	public boolean handle(Event event) {		
		InputEvent e = (InputEvent)event;
		switch(e.getType()){
//		case enter:
		case touchDown:
			setGraphic(pressedTex);
			setPressed(true);
			break;
		case exit:
			setGraphic(normalTex);
			setPressed(false);
			break;
		}
		return false;
	}
	
	public DefaultController getController(){
		return ctrl;
	}
	
	public boolean isPressed(){
		return isPressed;
	}
	
	public void setPressed(boolean pressed){
		this.isPressed = pressed;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		Stage parent = getStage();
		Camera c = parent.getCamera();
		
		stagePos.set(screenPos);
		stagePos.add(c.position.x, c.position.y);
		
		setPosition(stagePos.x, stagePos.y);
	}
}
