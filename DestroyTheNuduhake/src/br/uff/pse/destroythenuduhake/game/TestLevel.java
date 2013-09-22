package br.uff.pse.destroythenuduhake.game;

public class TestLevel extends Level{
	
	private GraphicAsset raiTex;
	private LevelObject raiObject;
	
	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);
		
		raiTex = b.getGraphicAsset(AssetIDs.MARIO);
		raiObject = new LevelObject(800/2 - 64/2, 20, raiTex);
		
		
		addObject(raiObject);
		
		addObject(new LevelObject(0, 0, b.getGraphicAsset(AssetIDs.SHELL)));
	}

	@Override
	public void dispose() {
		super.dispose();
		raiTex.dispose();
	}
}
