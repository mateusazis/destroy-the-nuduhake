package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.AssetBundle;

public class DTNGameBundle extends AssetBundle{

	public DTNGameBundle(String path){
		super(path);
		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO,"kibe"));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL,"kibe"));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_GROUND,"kibe"));
		
		addAsset(new SoundAsset(AssetIDs.SOUND_CHOICE,"kibe"));
		
		addAsset(new MusicAsset(AssetIDs.MUSIC_JUNGLE,"kibe"));
	}	
}
