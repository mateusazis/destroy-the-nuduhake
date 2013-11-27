package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicAsset extends Asset implements AudioAsset{

	/**
	 * 
	 */
	private static final long serialVersionUID = -926572852729136113L;
	private Music music;
	public MusicAsset(AssetID id, String filePath){
		super(id,filePath);
	}
	
	private MusicAsset(AssetID id, String filePath, Author author){
		super(id,filePath, author);
	}
	
	@Override
	public MusicAsset makeCopy(Author author, String newPath) {
		return new MusicAsset(getId(), newPath, author);
	}
	
	@Override
	public void load() {
		music = Gdx.audio.newMusic(getFileHandle());
	};
	
	@Override
	public void dispose() {
		music.dispose();
	}
	
	@Override
	public void play() {
		music.play();
		music.setLooping(true);
	}
	
	@Override
	public void stop() {
		music.stop();
	}
}