package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class TestBundle extends AssetBundle{

	public TestBundle(){
		super();

		Asset [] originalAssets = AssetDatabase.getEditableBuiltinAssets();
		for(Asset a : originalAssets)
			addAsset(a);
		
		Asset [] privateAssets = AssetDatabase.getPrivateBuiltinAssets();
		for(Asset a : privateAssets)
			addAsset(a);
	}
}
