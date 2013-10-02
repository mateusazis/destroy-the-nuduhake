package br.uff.pse.destroythenuduhake.game;

import java.util.List;
import java.util.LinkedList;

public class AssetBundle {

	private List<Asset> assets;
	private String path;
	
	public AssetBundle(String path){
		assets = new LinkedList<Asset>();
		this.path = path;
	}
	
	public void addAsset(Asset newAsset){
		assets.add(newAsset);
	}
	
	private Asset simpleGetAsset(int id){
		for(Asset a : assets)
			if(a.getId() == id)
				return a;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Asset> T getAsset(int id){
		return (T)simpleGetAsset(id);
	}
	
	public String getPath(){
		return path;
	}
	
	public void load(AssetBundle defaultBundle){
		for(Asset a : assets){
			String path = getPath();
			if(!a.exists(path))
				path = defaultBundle.getPath();
			a.load(path);
		}
	}
	
	public void dispose(){
		for(Asset a : assets)
			a.dispose();
	}	
}
