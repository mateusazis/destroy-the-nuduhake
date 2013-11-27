package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundAsset extends Asset implements AudioAsset{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5591686678247537188L;
	private Sound sound;
	
	public SoundAsset(AssetID id, String filePath){
		super(id,filePath);
	}
	
	private SoundAsset(AssetID id, String filePath, Author author){
		super(id,filePath, author);
	}
	
	@Override
	public SoundAsset makeCopy(Author author, String newPath) {
		return new SoundAsset(getId(), newPath, author);
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
	public void play() {
		sound.play();
	}
	
	@Override
	public void stop() {
		sound.stop();
	}
}