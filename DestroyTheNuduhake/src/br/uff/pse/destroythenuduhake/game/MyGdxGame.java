package br.uff.pse.destroythenuduhake.game;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture raiTexture;
	private Texture choiceTexture;
	private Rectangle rai;
	private Rectangle choice;
	private Sound choiceSound;

	@Override
	public void create() {
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		Texture.setEnforcePotImages(false);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		raiTexture = new Texture(Gdx.files.internal("images/rai.png"));
		raiTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		choiceTexture = new Texture(Gdx.files.internal("images/choice.png"));
		choiceTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		choiceSound = Gdx.audio.newSound(Gdx.files.internal("sounds/choice.mp3"));

		rai = new Rectangle();
		rai.x = 800 / 2 - 64 / 2;
		rai.y = 20;
		rai.width = 64;
		rai.height = 64;
		
		
		choice = new Rectangle();
		choice.x = 0;
		choice.y = 0;
		choice.width = 128;
		choice.height = 128;
	}

	@Override
	public void dispose() {
		batch.dispose();
		raiTexture.dispose();
		choiceTexture.dispose();
		choiceSound.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(raiTexture, rai.x, rai.y, rai.width, rai.height);
		batch.draw(choiceTexture, choice.x, choice.y, choice.width, choice.height);
		batch.end();
		
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			rai.x = touchPos.x - 64 / 2;
			rai.y = touchPos.y - 64 / 2;
		}
		
		if(rai.overlaps(choice)) {
            choiceSound.play();
         }

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
