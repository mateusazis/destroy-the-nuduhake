package br.uff.pse.destroythenuduhake.game.control;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;

public class AssetBundle {

	private List<Asset> assets;
	private LoadStrategy loadStrategy;
	
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
	
	protected void loadAllAssets(){
		loadStrategy = new LoadAllAssetsStrategy();
		loadStrategy.load();
	}
	
	protected void loadAssets(Set<AssetID> ids){
		loadStrategy = new LoadRequiredAssetsStrategy(ids);
		loadStrategy.load();
	}
	
	protected void dispose(){
		loadStrategy.dispose();
		loadStrategy = null;
	}
	
	private interface LoadStrategy{
		public void load();
		public void dispose();
	}
	
	private class LoadAllAssetsStrategy implements LoadStrategy{
		@Override
		public void load(){
			Gdx.app.log("", "loading all assets");
			for(Asset a : assets)
				a.load();
		}
		
		@Override
		public void dispose(){
			Gdx.app.log("", "disposing all assets");
			for(Asset a : assets)
				a.dispose();
		}
	}
	
	private class LoadRequiredAssetsStrategy implements LoadStrategy{
		
		Set<AssetID> ids;
		public LoadRequiredAssetsStrategy(Set<AssetID> ids){
			this.ids = ids;
		}
		
		@Override
		public void load(){
			Gdx.app.log("", "loading required assets");
			for(Asset a : assets)
			{
				if(ids.contains(a.getId()))
					a.load();
			}
		}
		
		@Override
		public void dispose(){
			Gdx.app.log("", "disposing required assets");
			for(Asset a : assets)
			{
				if(ids.contains(a.getId()))
					a.dispose();
			}
		}
	}
}
