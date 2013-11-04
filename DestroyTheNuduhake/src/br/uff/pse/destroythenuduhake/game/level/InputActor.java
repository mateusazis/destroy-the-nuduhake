package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class InputActor extends FixedObject implements EventListener {

	private GraphicAsset normalTex, pressedTex;
	private boolean isPressed = false;
	private DefaultController ctrl;
	
	public InputActor(float screenX, float screenY, GraphicAsset normalAsset, GraphicAsset pressedAsset, DefaultController controller){
		super(screenX, screenY, normalAsset);
		
		this.ctrl = controller;
		
		normalTex = normalAsset;
		pressedTex = pressedAsset;
		
		addListener(this);
	}

	@Override
	public boolean handle(Event event) {
		InputEvent e = (InputEvent)event;
		switch(e.getType()){
		case touchDown:
			setPressed(true);
			break;
		case touchUp:
		case exit:
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
		setGraphic(pressed ? pressedTex : normalTex);
	}
}
