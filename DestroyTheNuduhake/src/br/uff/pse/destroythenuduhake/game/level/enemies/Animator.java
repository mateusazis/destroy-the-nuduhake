package br.uff.pse.destroythenuduhake.game.level.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animator extends Actor{

	private int	frameCols, frameRows;
	private float totalDuration;
	
	private Animation 			walkAnimation;		// #3
	private Texture 			walkSheet;		// #4
//	private 			walkFrames;		// #5
//	private SpriteBatch			spriteBatch;		// #6
//	private ;		// #7
	
	private float stateTime;					// #8
	
	public Animator(int cols, int rows, float totalDuration, String fileName){
		super();
		this.frameCols = cols;
		this.frameRows = rows;
		walkSheet = new Texture(Gdx.files.internal(fileName));	// #9
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
frameCols, walkSheet.getHeight() / frameRows);				// #10
		TextureRegion[] walkFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		
		int frameCount = cols * rows;
		this.totalDuration = totalDuration;
		walkAnimation = new Animation(totalDuration / frameCount, walkFrames);		// #11
//		spriteBatch = new SpriteBatch();				// #12
		stateTime = 0f;							// #13
		
		setSize(walkSheet.getWidth() / cols, walkSheet.getHeight() / rows);
	}

	public void draw(SpriteBatch batch, float parentAlpha){
		stateTime += Gdx.graphics.getDeltaTime();			// #15
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);	// #16
//		spriteBatch.begin();
		
		float w = getWidth() * getScaleX();
		float h = getHeight() * getScaleY();
		batch.draw(currentFrame, getX() - w /2f, getY(), w, h);				// #17
		if(stateTime >= totalDuration)
			onEnded();
//		spriteBatch.end();
	}
	
	public void dispose(){
		walkAnimation = null;
		walkSheet.dispose();
	}
	
	protected void onEnded(){}
}
