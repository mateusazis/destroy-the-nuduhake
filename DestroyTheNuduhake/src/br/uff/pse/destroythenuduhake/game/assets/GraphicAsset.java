package br.uff.pse.destroythenuduhake.game.assets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.game.control.Asset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicAsset extends Asset{

	private Texture texture;
	
	public GraphicAsset(int id,String filepath,String a,boolean b){
		super(id,filepath,a,b);
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
	public String getAssetPath() {
		return AssetIDs.getSpritePath(getId());
	}
	
	public Bitmap getBitmap()
	{
		return BitmapFactory.decodeFile(getDataFilePath());
	}
	
	@Override
	protected String getDataFilePath(){
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
	public byte[] getBitmapBytes()
	{
		byte[] byteArray = null;
		try 
		{
			Bitmap bm = getBitmap();
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