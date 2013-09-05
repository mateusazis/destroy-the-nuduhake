package br.uff.pse.destroythenuduhake.game;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import android.opengl.GLES20;
import android.util.Log;

public class AnalogPad extends Entity implements IAnalogOnScreenControlListener{

	private float valueX, valueY;
	private static final float BORDER_OFFSET = 15;
	private AnalogOnScreenControl control;
	private Scene s;
	
	public AnalogPad(Camera c, Scene s, BaseGameActivity e){
		super();
		this.s = s;
		
		BitmapTextureAtlas tex = new BitmapTextureAtlas(e.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		TextureRegion baseRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex, e, "example_gfx/onscreen_control_base.png", 0, 0);
		TextureRegion knobRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex, e, "example_gfx/onscreen_control_knob.png", 128, 0);
		tex.load();
		
		
		float x1 = BORDER_OFFSET;
		float y1 = c.getHeight() - baseRegion.getHeight() - BORDER_OFFSET;
		
		control = new AnalogOnScreenControl(x1, y1, c, baseRegion, knobRegion, 0.1f, e.getVertexBufferObjectManager(), this);
		control.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		control.getControlBase().setAlpha(0.5f);
	}
	
	public float getX(){
		return valueX;
	}
	
	public float getY(){
		return valueY;
	}
	
	@Override
	public void onAttached() {
		super.onAttached();
		s.setChildScene(control);
	}
	
	@Override
	public void onDetached() {
		// TODO Auto-generated method stub
		super.onDetached();
		s.setChildScene(null);
	}
	
	
	@Override
	public void onControlChange(BaseOnScreenControl pBaseOnScreenControl,
			float pValueX, float pValueY) {
		this.valueX = pValueX;
		this.valueY = pValueY;
	}

	@Override
	public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
		
		
	}
}
