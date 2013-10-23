package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundAsset extends Asset implements AudioAsset{

	private Sound sound;
	
	public SoundAsset(AssetID id, String filePath){
		super(id,filePath);
	}
	
	private SoundAsset(AssetID id, String filePath, String authorName){
		super(id,filePath, authorName);
	}
	
	@Override
	public SoundAsset makeCopy(String authorName, String newPath) {
		return new SoundAsset(getId(), newPath, authorName);
	}
	
	@Override
	public void load() {
		sound = Gdx.audio.newSound(getFileHandle());
	};
	
	@Override
	public void dispose() {
		sound.dispose();
	}
	
//	@Override
//	public String getDataFilePath(){
//		return getFilePath() + ".mp3";
//	}
	
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
}