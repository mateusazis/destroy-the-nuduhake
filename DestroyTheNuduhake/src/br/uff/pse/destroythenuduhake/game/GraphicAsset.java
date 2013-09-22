package br.uff.pse.destroythenuduhake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicAsset extends Asset{

	private Texture texture;
	
	public GraphicAsset(int id){
		super(id);
	}
	
	@Override
	public void load(String bundlePath) {
		texture = new Texture(getFileHandle(bundlePath));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	};
	
	@Override
	public void dispose() {
		texture.dispose();
	}
	
	public void render(SpriteBatch batch, float x, float y){
		batch.draw(texture, x, y);
	}
	
	@Override
	public String getFolderPath() {
		return "images/";
	}
}