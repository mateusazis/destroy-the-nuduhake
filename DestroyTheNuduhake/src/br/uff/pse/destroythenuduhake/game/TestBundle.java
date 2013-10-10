package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class TestBundle extends AssetBundle{

	public TestBundle(){
		super();

		Asset [] originalAssets = AssetDatabase.getOriginalAssets();
		for(Asset a : originalAssets)
			addAsset(a);
	}
}
