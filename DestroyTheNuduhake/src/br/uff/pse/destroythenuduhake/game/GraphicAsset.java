package br.uff.pse.destroythenuduhake.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class GraphicAsset extends Asset{

	private Texture texture;
	
	public GraphicAsset(String path){
		this(Gdx.files.internal(path));
	}
	
	public GraphicAsset(FileHandle handle){
		texture = new Texture(handle);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	@Override
	public void dispose() {
		texture.dispose();
	}
	
	public void render(SpriteBatch batch, float x, float y){
		batch.draw(texture, x, y);
	}
	
}
