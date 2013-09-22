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
	
	public Game(){
		Texture.setEnforcePotImages(false);
		levels = new ArrayList<Level>();
	}
	
	public void changeLevel(int levelNumber){
		if(currentLevel != null)
			currentLevel.dispose();
		currentLevel = levels.get(levelNumber);
		currentLevel.create();
	}

	@Override
	public void create() {Level l = new TestLevel();
		levels.add(l);
		changeLevel(0);
	}

	@Override
	public void dispose() {
		if(currentLevel != null)
			currentLevel.dispose();
	}

	@Override
	public void render() {
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
