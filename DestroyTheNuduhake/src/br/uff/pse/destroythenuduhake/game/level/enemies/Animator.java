package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animator extends Actor{

	private int	frameCols, frameRows;
	private float totalDuration;
	
	private Animation walkAnimation;
	
	private float stateTime;
	
	public Animator(int cols, int rows, float totalDuration, GraphicAsset img){
		this.frameCols = cols;
		this.frameRows = rows;
		Texture walkSheet = img.getTexture();
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
frameCols, walkSheet.getHeight() / frameRows);
		TextureRegion[] walkFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		
		int frameCount = cols * rows;
		this.totalDuration = totalDuration;
		walkAnimation = new Animation(totalDuration / frameCount, walkFrames);
		stateTime = 0f;
		
		setSize(walkSheet.getWidth() / cols, walkSheet.getHeight() / rows);
	}

	public void draw(SpriteBatch batch, float parentAlpha){
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		
		float w = getWidth() * getScaleX();
		float h = getHeight() * getScaleY();
		batch.draw(currentFrame, getX() - w /2f, getY(), w, h);
		if(stateTime >= totalDuration)
			onEnded();
	}
	
	public void dispose(){
		walkAnimation = null;
	}
	
	protected void onEnded(){}
}
