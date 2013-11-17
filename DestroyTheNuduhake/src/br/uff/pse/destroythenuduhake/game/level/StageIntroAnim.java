package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class StageIntroAnim extends Group{

	private LevelObject topBar, middleBar, bottomBar;
	private enum State {IN, STAY, OUT};
	private State state = State.IN;
	
	private Interpolator interpolator;
	
	private static final float IN_DURATION = 3f, STAY_DURATION = 5f, OFF_DURATION = 1.5f;
	private static final float BAR_TOP_X_OFF = -1200, BAR_MIDDLE_X_OFF = -1600, BAR_BOTTOM_X_OFF = -2000, BAR_X_CENTER = -400;
	private BitmapFont f;
	private Label l;
	
	private static final float TEXT_SCALE = 2f;
	
	public StageIntroAnim(AssetBundle b){
		super();
		
		topBar = new LevelObject(BAR_X_CENTER, 85, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INTRO_BAR_TOP));
		middleBar = new LevelObject(BAR_X_CENTER, -80, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INTRO_BAR_MIDDLE));
		bottomBar = new LevelObject(BAR_X_CENTER, -95, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_INTRO_BAR_BOTTOM));
		
		addActor(topBar);
		addActor(middleBar);
		addActor(bottomBar);
		
		interpolator = new Interpolator(IN_DURATION);
		
		f = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		f.setScale(TEXT_SCALE);
		f.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		l = new Label("Stage 1", new Label.LabelStyle(f, Color.WHITE));
		addActor(l);
		l.setVisible(false);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		Stage parent = getStage();
		Camera c = parent.getCamera();
		
		setPosition(c.position.x, c.position.y);
		if(delta < 1f)
			interpolator.update(delta);
		float value = interpolator.getValue();
		switch(state){
		case IN:
			topBar.setX(lerp(value, BAR_TOP_X_OFF, BAR_X_CENTER));
			middleBar.setX(lerp(value, BAR_MIDDLE_X_OFF, BAR_X_CENTER));
			bottomBar.setX(lerp(value, BAR_BOTTOM_X_OFF, BAR_X_CENTER));
			if(value == 1f){
				state = State.STAY;
				interpolator.reset(STAY_DURATION);
				l.setVisible(true);
			}
			break;
		case STAY:
			int div = (int)(10f * value / 0.4f);
			l.setFontScale(div % 2 == 0? TEXT_SCALE : TEXT_SCALE * 1.1f);
			
			TextBounds bounds = l.getTextBounds();
			l.setPosition(- bounds.width / 4f, -bounds.height / 2f);
			
			
			if(value == 1f){
				state = State.OUT;
				interpolator.reset(OFF_DURATION);
				l.setVisible(false);
			}
			
			break;
		case OUT:
			topBar.setX(lerp(value, BAR_X_CENTER, BAR_TOP_X_OFF));
			middleBar.setX(lerp(value, BAR_X_CENTER, BAR_MIDDLE_X_OFF));
			bottomBar.setX(lerp(value, BAR_X_CENTER, BAR_BOTTOM_X_OFF));
			if(value == 1f){
				state = State.IN;
				
				TestLevel l = (TestLevel)getStage();
				l.setInputEnabled(true);
				remove();
				f.dispose();
			}
			break;
		}
		
	}
	
	private static float lerp(float value, float from, float to){
		return from + (to - from) * value;
	}
}
