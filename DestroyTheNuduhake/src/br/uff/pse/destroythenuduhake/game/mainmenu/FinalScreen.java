package br.uff.pse.destroythenuduhake.game.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;

public class FinalScreen extends Level{

	private BitmapFont f;
	private Label l;
	
	@Override
	public void createWithAssetBundle(AssetBundle bundle) {
		super.createWithAssetBundle(bundle);
		f = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		l = new Label("Thanks for playing!", new Label.LabelStyle(f, Color.WHITE));
		addActor(l);
		l.setPosition(400 - l.getWidth() / 2f, 240);
		l.setAlignment(Align.center, Align.center);
		
		Label l2 = new Label("Play again!", new Label.LabelStyle(f, Color.YELLOW));
		addActor(l2);
		l2.setPosition(400 - l2.getWidth() / 2f, 50);
		l2.setAlignment(Align.center, Align.center);
		l2.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				InputEvent e = (InputEvent)event;
				if(e.getType().equals(InputEvent.Type.touchDown))
					getParent().changeLevel(1);
				return false;
			}
		});
	}
	
	@Override
	public void dispose() {
		super.dispose();
		f.dispose();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
