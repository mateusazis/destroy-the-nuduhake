package br.uff.pse.destroythenuduhake.game;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.*;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.opengl.GLES20;
import android.util.Log;

public class Main extends BaseGameActivity {
	
	BitmapTextureAtlas texture, mOnScreenControlTexture;
	ITextureRegion region, mOnScreenControlBaseTextureRegion, mOnScreenControlKnobTextureRegion;
	Scene s;
	private float cameraW = 800, cameraH = 480;
	private Camera c;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		c = new Camera(0,  0, cameraW, cameraH);
		IResolutionPolicy policy = new RatioResolutionPolicy(cameraW, cameraH);
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, policy, c);
		return options;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		loadGraphics();
		loadSounds();
		loadFonts();
		Log.d("", "create resources");
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
	
	private void loadGraphics(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("images/");
		texture = new BitmapTextureAtlas(getTextureManager(), 256, 256, TextureOptions.DEFAULT);
		region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texture, this, "mario.png", 0, 0);
		texture.load();
		
//		this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
//		this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "example_gfx/onscreen_control_base.png", 0, 0);
//		this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "example_gfx/onscreen_control_knob.png", 128, 0);
//		this.mOnScreenControlTexture.load();
	}
	
	private void loadSounds(){
		
	}

	private void loadFonts(){
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		s = new Scene();
		Log.d("", "create scene");
		pOnCreateSceneCallback.onCreateSceneFinished(s);

	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		
		final Sprite sprite = new Sprite(0,  0, region, getVertexBufferObjectManager());
		s.attachChild(sprite);
		
		final AnalogPad pad = new AnalogPad(c, s, this);
		s.attachChild(pad);
		
		final float SPEED = 400;
		sprite.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float delta) {
				float x = sprite.getX(), y = sprite.getY();
				sprite.setPosition(x + SPEED * delta * pad.getX(), y + SPEED * delta * pad.getY());
				
			}
		});
		
		
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

}
