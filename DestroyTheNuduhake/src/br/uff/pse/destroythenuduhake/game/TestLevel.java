package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;

import com.badlogic.gdx.Gdx;

public class TestLevel extends Level{
	
	private GraphicAsset raiTex;
	private LevelObject raiObject;
	private SoundAsset choiceSound;
	private MusicAsset music;
	
	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);
		
		raiTex = b.getAsset(AssetIDs.SPRITE_MARIO);
		raiObject = new LevelObject(800/2 - 64/2, 20, raiTex);
		
//		choiceSound = b.getAsset(AssetIDs.SOUND_CHOICE);
//		music = b.getAsset(AssetIDs.MUSIC_JUNGLE);
//		music.play();
		
		addObject(raiObject);
		
		addObject(new LevelObject(0, 0, b.<GraphicAsset>getAsset(AssetIDs.SPRITE_SHELL)));
	}
	

	
//	@Override
//	public void act(float delta) {
//		// TODO Auto-generated method stub
//		super.act(delta);
//		if(Gdx.input.justTouched()){
//			choiceSound.play();
//		}
//	}
	
	

	@Override
	public void dispose() {
		super.dispose();
		raiTex.dispose();
	}
}
