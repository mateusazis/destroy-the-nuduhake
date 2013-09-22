package br.uff.pse.destroythenuduhake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Asset implements Disposable{

	private int id;
	private int versionNumber;
	private String author;
	
	public Asset(int id){
		this.id = id;
	}
	
	public abstract void load(String bundlePath);
	public abstract void dispose();
	public abstract String getFolderPath();
	
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
		pathBuilder.append(AssetIDs.getFileName(getId()));
		
		return Gdx.files.internal(pathBuilder.toString());
	}
}
