package br.uff.pse.destroythenuduhake.game.assets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.game.Asset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
	@Override
	public int getPayload(byte[] bytes)
	{
		int x = 0;
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			ois.readObject();
			x = ois.read(bytes, 0, bytes.length);
			ois.close();
			
		
		
		} 
		catch(Exception e)
		{
			Exception y = e;
		}
		return x;
		
	}
	public Bitmap getBitmap()
	{
		byte[] bytes = new byte[10000000];
		int x  = getPayload(bytes);
		Bitmap ret = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return ret;
	}
	public void saveBitmap(Bitmap bm)
	{
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(this);
			boolean x = bm.compress(Bitmap.CompressFormat.JPEG, 90, oos);
			oos.flush();
			oos.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}