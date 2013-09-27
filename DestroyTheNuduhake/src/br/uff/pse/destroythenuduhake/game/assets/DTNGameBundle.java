package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.AssetBundle;

public class DTNGameBundle extends AssetBundle{

	public DTNGameBundle(String path){
		super(path);
		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL));
		
		addAsset(new SoundAsset(AssetIDs.SOUND_CHOICE));
		
		addAsset(new MusicAsset(AssetIDs.MUSIC_JUNGLE));
	}	
}
