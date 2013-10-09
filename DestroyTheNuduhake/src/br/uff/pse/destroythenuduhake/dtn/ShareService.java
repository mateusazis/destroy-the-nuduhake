package br.uff.pse.destroythenuduhake.dtn;

import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public interface ShareService 
{
	public void start(BundleReceiver receiver, AssetBundle[] bundles);

}
