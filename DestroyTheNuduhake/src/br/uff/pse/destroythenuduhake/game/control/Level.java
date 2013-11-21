package br.uff.pse.destroythenuduhake.game.control;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level extends Stage implements ApplicationListener{
	private AssetBundle usedBundle;
	private Game parent;
	private Set<AssetID> requiredAssets;
	private List<LevelObject> disposeList, overlapableList;
	
	public Level(){
		super(800, 480, false, null);
		disposeList = new LinkedList<LevelObject>();
		overlapableList = new LinkedList<LevelObject>();
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
		overlapableList.clear();
		disposeList.clear();
	}

	@Override
	public void render() {
		
		if(disposeList.size() > 0){
			for(LevelObject obj : disposeList)
				obj.dispose();
			disposeList.clear();
		}
		
		checkOverlaps();//
//		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		Gdx.app
//		super.setViewport(800, 480, false);
//		super.setViewport(800, 480, false, 0, 0, 800, 480);
		draw();
		
	}
	
	/**
	 * Verifica sobreposi��o entre os ret�ngulos dos LevelObjects.
	 */
	private void checkOverlaps(){
		for(int i = 0; i < overlapableList.size() - 1; i++){
			LevelObject oI = overlapableList.get(i);
			Rectangle r = oI.getRect();
			for(int j = i+1; j < overlapableList.size(); j++){
				LevelObject oJ = overlapableList.get(j);
				if(r.overlaps(oJ.getRect())){
					oI.onOverlap(oJ);
					oJ.onOverlap(oI);
				}
			}
		}
	}
	
	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		if(actor instanceof LevelObject){
			LevelObject obj = (LevelObject)actor;
			if(obj.isOverlapable())
				overlapableList.add(obj);
		}
	}

	@Override
	public void resize(int width, int height) {
//		super.setViewport(width, height, false);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public void addToDisposeList(LevelObject levelObject) {
		disposeList.add(levelObject);
		overlapableList.remove(levelObject);
	}
}
