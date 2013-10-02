package br.uff.pse.destroythenuduhake.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level extends Stage implements ApplicationListener{
	
	private List<LevelObject> objects;
	private AssetBundle usedBundle;
	private Game parent;
	
	public Level(){
		super();
	}
	
	protected void setParent(Game g){
		this.parent = g;
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
		bundle.load(parent.getDefaultBundle());
	}

	public void definitiveDispose(){
		super.dispose(); 
		//scene2d's dispose clears a sprite batch.
		//Only call it if this level is never to be used again
	}
	
	@Override
	public void dispose() {
		//do NOT call super.dispose! It disposes a sprite batch necessary
		//for rendering! Instead, use definitiveDispose when you are done!
		super.clear();
		objects.clear();
		usedBundle.dispose();
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
