package br.uff.pse.destroythenuduhake.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level extends Stage implements ApplicationListener{
	
	private List<LevelObject> objects;
	private AssetBundle usedBundle;
	
	public Level(){
		super();
	}
	
	public void addObject(LevelObject obj){
		objects.add(obj);
		addActor(obj);
	}
	
	public void removeObject(LevelObject obj){
		objects.add(obj);
		getActors().removeValue(obj, true);
	}
	
	@Override
	public final void create() {
		createWithAssetBundle(DefaultBundle.getInstance());
	}
	
	public void createWithAssetBundle(AssetBundle bundle){
		objects = new ArrayList<LevelObject>();
		usedBundle = bundle;
		bundle.load();
	}

	@Override
	public void dispose() {
		super.dispose();
		usedBundle.dispose();
		objects.clear();
		getActors().clear();
		objects = null;
		usedBundle = null;
	}

	@Override
	public void render() {
		super.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
