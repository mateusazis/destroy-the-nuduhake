package br.uff.pse.destroythenuduhake.game;

public class TestBundle extends AssetBundle{

	public TestBundle(){
		super("test_bundle/");
		addAsset(new GraphicAsset(AssetIDs.MARIO));
	}
	
}
