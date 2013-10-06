package br.uff.pse.destroythenuduhake.game;

import com.badlogic.gdx.Gdx;

import android.content.Context;
import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.DTNGameBundle;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.files.FileManager;

public class TestBundle extends DTNGameBundle{

	public TestBundle(){
		super();

		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO, "images/mario"));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL, "images/shell"));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_GROUND, "images/ground"));
		addAsset(new GraphicAsset(AssetIDs.SPRITE_DRAGON, "images/dragon"));
		
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_MARIO,"kibe"));
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_SHELL,"kibe"));
//		addAsset(new GraphicAsset(AssetIDs.SPRITE_GROUND,"kibe"));
		
//		addAsset(new SoundAsset(AssetIDs.SOUND_CHOICE,"kibe"));
//		
//		addAsset(new MusicAsset(AssetIDs.MUSIC_JUNGLE,"kibe"));
	}
}
