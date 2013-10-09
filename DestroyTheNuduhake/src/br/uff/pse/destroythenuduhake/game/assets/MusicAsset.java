package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicAsset extends Asset implements AudioAsset{

	private Music music;

	
	public MusicAsset(int id,String filePath){
		super(id,filePath);
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
	protected String getDataFilePath(){
		return getFilePath() + ".mp3";
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