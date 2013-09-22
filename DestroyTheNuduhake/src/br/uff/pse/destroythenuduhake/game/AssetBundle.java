package br.uff.pse.destroythenuduhake.game;

import java.util.List;
import java.util.LinkedList;

public class AssetBundle {

	private List<Asset> assets;
	private String path;
	
	public AssetBundle(String path){
		assets = new LinkedList<Asset>();
		this.path = path;
		

		addAsset(new GraphicAsset(AssetIDs.MARIO));
		addAsset(new GraphicAsset(AssetIDs.SHELL));
	}
	
	public void addAsset(Asset newAsset){
		assets.add(newAsset);
	}
	
	public Asset getAsset(int id){
		for(Asset a : assets)
			if(a.getId() == id)
				return a;
		return null;
	}
	
	public GraphicAsset getGraphicAsset(int id){
		return (GraphicAsset)getAsset(id);
	}
	
	public String getPath(){
		return path;
	}
	
	private String getDefaultPath(){
		return "default/";
	}
	
	public void load(){
		for(Asset a : assets){
			String path = getPath();
			if(!a.exists(path))
				path = getDefaultPath();
			a.load(path);
		}
	}
	
	public void dispose(){
		for(Asset a : assets)
			a.dispose();
	}	
}
