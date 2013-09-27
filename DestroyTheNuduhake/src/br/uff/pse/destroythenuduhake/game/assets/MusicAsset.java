package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicAsset extends Asset implements AudioAsset{

	private Music music;
	
	public MusicAsset(int id){
		super(id);
	}
	
	@Override
	public void load(String bundlePath) {
		music = Gdx.audio.newMusic(getFileHandle(bundlePath));
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
	
	@Override
	public String getFolderPath() {
		return "musics/";
	}
	
	@Override
	public String getAssetPath() {
		return AssetIDs.getMusicPath(getId());
	}
}