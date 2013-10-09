package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

public class TestBundle extends AssetBundle{

	public TestBundle(){
		super();

		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO, "images/mario","autor",true));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL, "images/shell","autor",true));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_GROUND, "images/ground","autor",true));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_DRAGON, "images/dragon","autor",true));
		
		addAsset(new MusicAsset(AssetIDs.MUSIC_OPENING, "musics/opening","autor",true));
		
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO,"kibe"));
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL,"kibe"));
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_GROUND,"kibe"));
		
//		addAsset(new SoundAsset(AssetIDs.SOUND_CHOICE,"kibe"));
//		
//		addAsset(new MusicAsset(AssetIDs.MUSIC_JUNGLE,"kibe"));
	}
}
