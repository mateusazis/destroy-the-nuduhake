package br.uff.pse.destroythenuduhake.game.control;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level extends Stage implements ApplicationListener{
	private AssetBundle usedBundle;
	private Game parent;
	
	public Level(){
		super();
	}
	
	protected void setParent(Game g){
		this.parent = g;
	}
	
	public Game getParent(){
		return parent;
	}
	
	@Override
	public final void create() {
//		createWithAssetBundle(DefaultBundle.getInstance());
	}
	
	public void createWithAssetBundle(AssetBundle bundle){
		usedBundle = bundle;
		bundle.load();
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
		usedBundle.dispose();
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