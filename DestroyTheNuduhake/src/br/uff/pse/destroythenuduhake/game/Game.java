package br.uff.pse.destroythenuduhake.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

public class Game implements ApplicationListener {
	private Level currentLevel;
	private List<Level> levels;
	private AssetBundle usedBundle;
	private int nextLevel = -1;
	
	public Game(AssetBundle bundle){
		this.usedBundle = bundle;
		Texture.setEnforcePotImages(false);
		levels = new ArrayList<Level>();
	}
	
	public void changeLevel(int levelNumber){
		nextLevel = levelNumber;
	}
	
	public int getCurrentLevel(){
		return levels.indexOf(currentLevel);
	}
	
	private void actualChangeLevel(){
		if(currentLevel != null)
			currentLevel.dispose();
		currentLevel = levels.get(nextLevel);
		currentLevel.createWithAssetBundle(usedBundle);
		nextLevel = -1;
	}

	private void addLevel(Level l){
		levels.add(l);
		l.setParent(this);
	}
	
	@Override
	public void create() {
		Level l = new TestLevel();
		addLevel(new MainMenu());
		addLevel(l);
		
		changeLevel(0);
	}
	
	public void setUsedBundle(AssetBundle newBundle){
		this.usedBundle = newBundle;
	}

	@Override
	public void dispose() {
		if(currentLevel != null)
			currentLevel.dispose();
		for(Level l : levels){
			l.setParent(null);
			l.definitiveDispose();
		}
		levels.clear();
		Gdx.app.debug("", "game disposed");
	}

	@Override
	public void render() {
		if(nextLevel != -1)
			actualChangeLevel();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(currentLevel != null){
			currentLevel.act(Gdx.graphics.getDeltaTime());
			currentLevel.render();
		}
	}

	@Override
	public void resize(int width, int height) {
		if(currentLevel != null)
			currentLevel.resize(width, height);
	}

	@Override
	public void pause() {
		if(currentLevel != null)
			currentLevel.pause();
	}

	@Override
	public void resume() {
		if(currentLevel != null)
			currentLevel.resume();
	}
}
