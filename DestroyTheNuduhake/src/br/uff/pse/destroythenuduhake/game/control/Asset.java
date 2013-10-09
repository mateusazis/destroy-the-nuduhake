package br.uff.pse.destroythenuduhake.game.control;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Asset implements Disposable,Serializable{

	private int id;
	private int versionNumber;
	private String author;
	private String filePath;
	
	public Asset(int id,String filePath){
		this.id = id;
		this.versionNumber = 0;
		this.setFilePath(filePath);
	}
	
	public abstract void load();
	public abstract void dispose();
	public abstract String getFolderPath();
	public abstract String getAssetPath();
	protected abstract String getDataFilePath();
	
	protected void markModification(){
		versionNumber++;
	}
	
	public int getId(){
		return id;
	}
	
	public int getVersionNumber(){
		return versionNumber;
	}
	
	public String getAuthor(){
		return author;
	}
	
	
	
	protected FileHandle getFileHandle(){
		return Gdx.files.internal(getDataFilePath());
//		StringBuilder pathBuilder = new StringBuilder("bundles/");
//		
//		pathBuilder.append(bundlePath);
//		pathBuilder.append(getFolderPath());
//		pathBuilder.append(getAssetPath());
//		
//		return Gdx.files.internal(pathBuilder.toString());
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
