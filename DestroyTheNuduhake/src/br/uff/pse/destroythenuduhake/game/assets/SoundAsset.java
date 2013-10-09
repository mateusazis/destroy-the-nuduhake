package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundAsset extends Asset implements AudioAsset{

	private Sound sound;
	
	public SoundAsset(int id,String filePath){
		super(id,filePath);
	}
	
	@Override
	public void load() {
		sound = Gdx.audio.newSound(getFileHandle());
	};
	
	@Override
	public void dispose() {
		sound.dispose();
	}
	
	@Override
	protected String getDataFilePath(){
		return getFilePath() + ".mp3";
	}
	
	@Override
	public void play() {
		sound.play();
	}
	
	@Override
	public void stop() {
		sound.stop();
	}
	
	@Override
	public String getFolderPath() {
		return "sounds/";
	}
	
	@Override
	public String getAssetPath() {
		return AssetIDs.getSoundPath(getId());
	}
}