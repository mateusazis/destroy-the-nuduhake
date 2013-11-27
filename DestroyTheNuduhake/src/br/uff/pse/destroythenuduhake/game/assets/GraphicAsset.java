package br.uff.pse.destroythenuduhake.game.assets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.game.control.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicAsset extends Asset{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114353327388248403L;
	private Texture texture;
	
	public GraphicAsset(AssetID id,String filepath){
		super(id,filepath);
	}
	
	private GraphicAsset(AssetID id,String filepath, Author author){
		super(id,filepath, author);
	}
	
	@Override
	public void load() {
		texture = new Texture(getFileHandle());
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	};
	
	public Texture getTexture(){
		return texture;
	}
	
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
	
	public void render(SpriteBatch batch, LevelObject a){
		TextureRegion region = a.getTextureRegion();
		
		float x = a.getX(),
				y = a.getY(),
				width = a.getWidth(),
				height = a.getHeight(),
				scaleX = a.getScaleX(),
				scaleY = a.getScaleY(),
				originX = a.getOriginX(),
				originY = a.getOriginY(),
				rotation = a.getRotation();
		batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
	}
	
	@Override
	public GraphicAsset makeCopy(Author author, String newPath) {
		return new GraphicAsset(getId(), newPath, author);
	}
	
	public Bitmap getBitmap(Context c)
	{
		if(isOriginal()){
			try {
				InputStream in = c.getAssets().open(getFilePath());
				Bitmap resp = BitmapFactory.decodeStream(in);
				in.close();
				return resp;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return BitmapFactory.decodeFile(getFilePath());
	}
	public void editBitmap(Bitmap bm)
	{
		setBitmap(bm);
		markModification();
	}
	public void setBitmap(Bitmap bm)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(getFilePath());
			bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
			//markModification();
		} 
		catch (Exception e)
		{
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
			e.printStackTrace();
		}
		return byteArray;
	}
}