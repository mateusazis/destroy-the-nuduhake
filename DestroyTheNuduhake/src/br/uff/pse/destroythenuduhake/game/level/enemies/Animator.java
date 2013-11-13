package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Animator{

	private int	frameCols, frameRows;
	private float totalDuration;
	
	private Animation walkAnimation;
	
	private float stateTime;
	
	private float width, height;
	private float scale = 1;
	private Listener listener;
	
	public Animator(int cols, int rows, float totalDuration, GraphicAsset img){
		this(cols, rows, totalDuration, img, null);
	}
	
	public Animator(int cols, int rows, float totalDuration, GraphicAsset img, Listener l){
		this.frameCols = cols;
		this.frameRows = rows;
		this.listener = l;
		
		Texture walkSheet = img.getTexture();
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
frameCols, walkSheet.getHeight() / frameRows);
		TextureRegion[] walkFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) 
			for (int j = 0; j < frameCols; j++) 
				walkFrames[index++] = tmp[i][j];
		
		int frameCount = cols * rows;
		this.totalDuration = totalDuration;
		walkAnimation = new Animation(totalDuration / frameCount, walkFrames);
		walkAnimation.setPlayMode(Animation.LOOP);
		stateTime = 0f;
		
		width = walkSheet.getWidth() / cols;
		height = walkSheet.getHeight() / rows;
//		setSize(walkSheet.getWidth() / cols, walkSheet.getHeight() / rows);
	}

	public void draw(SpriteBatch batch, float x, float y){
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		
		float w = width * scale;
		float h = height * scale;
		batch.draw(currentFrame, x - w /2f, y, w, h);
		
		if(stateTime >= totalDuration && listener != null)
			listener.onEnded();
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public void dispose(){
		walkAnimation = null;
	}
	
	public static interface Listener{
		public void onEnded();
	}
}
