package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundAsset extends Asset implements AudioAsset{

	private Sound sound;
	
	public SoundAsset(int id){
		super(id);
	}
	
	@Override
	public void load(String bundlePath) {
		sound = Gdx.audio.newSound(getFileHandle(bundlePath));
	};
	
	@Override
	public void dispose() {
		sound.dispose();
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