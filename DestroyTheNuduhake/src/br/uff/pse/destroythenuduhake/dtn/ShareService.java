package br.uff.pse.destroythenuduhake.dtn;

import br.uff.pse.destroythenuduhake.game.AssetBundle;

public interface ShareService 
{
	public void start(BundleReceiver receiver, AssetBundle[] bundles);

}
