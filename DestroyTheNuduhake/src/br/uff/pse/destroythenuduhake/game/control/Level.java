package br.uff.pse.destroythenuduhake.game.control;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level extends Stage implements ApplicationListener{
	private AssetBundle usedBundle;
	private Game parent;
	private Set<AssetID> requiredAssets;
	private List<LevelObject> disposeList;
	
	public Level(){
		super();
		disposeList = new LinkedList<LevelObject>();
	}
	
	public void setRequiredAssets(AssetID...ids){
		requiredAssets = new HashSet<AssetID>();
		for(AssetID id : ids)
			requiredAssets.add(id);
	}
	
	private boolean shouldLoadAllAssets(){
		return requiredAssets == null;
	}
	
	protected void setParent(Game g){
		this.parent = g;
	}
	
	public Game getParent(){
		return parent;
	}
	
	@Override
	public final void create() {	}
	
	public void createWithAssetBundle(AssetBundle bundle){
		Gdx.input.setInputProcessor(this);
		usedBundle = bundle;
		
		if(shouldLoadAllAssets())
			bundle.loadAllAssets();
		else
			bundle.loadAssets(requiredAssets);
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
		if(usedBundle != null){
			usedBundle.dispose();
			usedBundle = null;
		}
	}

	@Override
	public void render() {
		if(disposeList.size() > 0){
			for(LevelObject obj : disposeList)
				obj.dispose();
			disposeList.clear();
		}
		draw();
	}

	@Override
	public void resize(int width, int height) {
		super.setViewport(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public void addToDisposeList(LevelObject levelObject) {
		disposeList.add(levelObject);
	}
}
