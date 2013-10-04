package br.uff.pse.destroythenuduhake.game.assets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.game.Asset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GraphicAsset extends Asset{

	private Texture texture;
	
	public GraphicAsset(int id,String filepath){
		super(id,filepath);
		
		
	}
	
	@Override
	public void load(String bundlePath) {
		texture = new Texture(getFileHandle(bundlePath));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	};
	
	public int getWidth(){
		return texture.getWidth();
	}
	
	public int getHeight(){
		return texture.getHeight();
	} 
	
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
	
	@Override
	public String getAssetPath() {
		return AssetIDs.getSpritePath(getId());
	}
	public Bitmap getBitmap()
	{
		return BitmapFactory.decodeFile(getFilePath()+".png");
	}
	public void setBitmap(Bitmap bm)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(getFilePath()+".png");
			bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
			
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}