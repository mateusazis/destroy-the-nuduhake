package br.uff.pse.destroythenuduhake.game.control;

import java.util.List;
import java.util.LinkedList;

public class AssetBundle {

	private List<Asset> assets;
	
	public AssetBundle(){
		assets = new LinkedList<Asset>();
	}
	
	public void addAsset(Asset newAsset){
		assets.add(newAsset);
	}
	
	public List<Asset> getAllAssets(){
		return assets;
	}
	
	private Asset simpleGetAsset(AssetID id){
		for(Asset a : assets)
			if(a.getId().equals(id))
				return a;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Asset> T getAsset(AssetID id){
		return (T)simpleGetAsset(id);
	}
	
	protected void load(){
		for(Asset a : assets)
			a.load();
	}
	
	protected void dispose(){
		for(Asset a : assets)
			a.dispose();
	}	
}
