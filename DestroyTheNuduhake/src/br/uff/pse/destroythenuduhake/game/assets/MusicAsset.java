package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicAsset extends Asset implements AudioAsset{

	private Music music;
	public MusicAsset(AssetID id, String filePath){
		super(id,filePath);
	}
	
	private MusicAsset(AssetID id, String filePath, String authorName){
		super(id,filePath, authorName);
	}
	
	@Override
	public MusicAsset makeCopy(String authorName, String newPath) {
		return new MusicAsset(getId(), newPath, authorName);
	}
	
	@Override
	public void load() {
		music = Gdx.audio.newMusic(getFileHandle());
	};
	
	@Override
	public void dispose() {
		music.dispose();
	}
	
//	@Override
//	public String getDataFilePath(){
//		return getFilePath() + ".mp3";
//	}
	
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
	
//	@Override
//	public String getAssetPath() {
//		return AssetDatabase.getMusicPath(getId());
//	}
}