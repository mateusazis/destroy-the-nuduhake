package br.uff.pse.destroythenuduhake.game.control;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Asset implements Disposable,Serializable{

	private AssetID id;
	private int versionNumber;
	private String author;
	private String filePath;
	private boolean original;
	
	public Asset(AssetID id, String filePath){
		this.id = id;
		this.versionNumber = 0;
		this.author = "Built-in";
		this.setFilePath(filePath);
		this.original = true;
	}
	
	public Asset(AssetID id,String filePath, String author){
		this.id = id;
		this.versionNumber = 0;
		this.author = author;
		this.setFilePath(filePath);
		this.original = false;
	}
	
	public abstract void load();
	public abstract void dispose();
	public abstract String getFolderPath();
//	public abstract String getAssetPath();
//	public abstract String getDataFilePath();
	public abstract Asset makeCopy(String authorName, String newPath);
	
	/**
	 * Compares each id in this list with this asset's own id, checking for any match.
	 * @param idList List of ids to be compared.
	 * @return true if any id in the list matches this assets's id.
	 */
	public boolean compareID(AssetID ... idList){
		for(AssetID cur : idList)
			if(cur.equals(id))
				return true;
		return false;
	}
	
	protected void markModification(){
		versionNumber++;
	}
	
	public AssetID getId(){
		return id;
	}
	
	public int getVersionNumber(){
		return versionNumber;
	}
	
	public String getAuthor(){
		return author;
	}
	
	protected FileHandle getFileHandle(){
		if(isOriginal())
			return Gdx.files.internal(getFilePath());
		return Gdx.files.absolute(getFilePath());
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

	public boolean isOriginal() {
		return original;
	}

}
