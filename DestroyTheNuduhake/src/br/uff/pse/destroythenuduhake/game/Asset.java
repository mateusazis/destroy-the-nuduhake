package br.uff.pse.destroythenuduhake.game;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Asset implements Disposable,Serializable{

	private int id;
	private int versionNumber;
	private String author;
	public String filePath;
	
	public Asset(int id){
		this.id = id;
	}
	
	public abstract void load(String bundlePath);
	public abstract void dispose();
	public abstract String getFolderPath();
	public abstract String getAssetPath();
	
	public int getId(){
		
		return id;
		
	}
	
	public int getVersionNumber(){
		return versionNumber;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public boolean exists(String bundlePath){
		return getFileHandle(bundlePath).exists();
	}
	
	protected FileHandle getFileHandle(String bundlePath){
		StringBuilder pathBuilder = new StringBuilder("bundles/");
		
		pathBuilder.append(bundlePath);
		pathBuilder.append(getFolderPath());
		pathBuilder.append(getAssetPath());
		
		return Gdx.files.internal(pathBuilder.toString());
	}
	public int getPayload(byte[] b)
	{
		
		return 0;
	}
	
}
