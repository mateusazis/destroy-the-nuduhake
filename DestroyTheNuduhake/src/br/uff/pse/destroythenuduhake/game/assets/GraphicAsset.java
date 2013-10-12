package br.uff.pse.destroythenuduhake.game.assets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicAsset extends Asset{

	private Texture texture;
	
	public GraphicAsset(AssetID id,String filepath){
		super(id,filepath);
	}
	
	private GraphicAsset(AssetID id,String filepath, String author){
		super(id,filepath, author);
	}
	
	@Override
	public void load() {
		texture = new Texture(getFileHandle());
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
	public GraphicAsset makeCopy(String authorName, String newPath) {
		return new GraphicAsset(getId(), newPath, authorName);
	}
//	@Override
//	public String getAssetPath() {
//		return AssetDatabase.getSpritePath(getId());
//	}
	
	public Bitmap getBitmap(Context c)
	{
		if(isOriginal()){
			try {
				InputStream in = c.getAssets().open(getDataFilePath());
				Bitmap resp = BitmapFactory.decodeStream(in);
				in.close();
				return resp;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return BitmapFactory.decodeFile(getDataFilePath());
	}
	
	@Override
	public String getDataFilePath(){
		return getFilePath() + ".png";
	}
	
	public void setBitmap(Bitmap bm)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(getDataFilePath());
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
	public byte[] getBitmapBytes(Context c)
	{
		byte[] byteArray = null;
		try 
		{
			Bitmap bm = getBitmap(c);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byteArray = stream.toByteArray();
			
			
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArray;
		
	}
}