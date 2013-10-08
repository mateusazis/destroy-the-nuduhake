package br.uff.pse.destroythenuduhake.game.assets;

import java.util.HashMap;

public final class AssetIDs {
	
	//sprites
	public static final int SPRITE_MARIO = 0x0000;
	public static final int SPRITE_SHELL = 0x0001;
	public static final int SPRITE_GROUND = 0x0002;
	public static final int SPRITE_DRAGON = 0x0003;
	
	//sounds
	public static final int SOUND_CHOICE = 0x0100;
	
	//musics
	public static final int MUSIC_OPENING = 0x0200;
	
	private static HashMap<Integer, String> spritePaths, soundPaths, musicPaths;
	
	private static void initializePathTable(){
		if(spritePaths == null){
			spritePaths = new HashMap<Integer, String>();
			soundPaths = new HashMap<Integer, String>();
			musicPaths = new HashMap<Integer, String>();
			
			spritePaths.put(AssetIDs.SPRITE_MARIO, "mario.png");
			spritePaths.put(AssetIDs.SPRITE_SHELL, "shell.png");
			spritePaths.put(AssetIDs.SPRITE_GROUND, "ground.png");
			
			soundPaths.put(AssetIDs.SOUND_CHOICE, "choice.mp3");
			
			musicPaths.put(AssetIDs.MUSIC_OPENING, "opening.aif");
		}
	}
	
	public static String getSpritePath(int id){
		initializePathTable();
		return spritePaths.get(id);
	}
	
	public static String getSoundPath(int id){
		initializePathTable();
		return soundPaths.get(id);
	}
	
	public static String getMusicPath(int id){
		initializePathTable();
		return musicPaths.get(id);
	}
	
	public static void dispose(){
		spritePaths.clear(); soundPaths.clear(); musicPaths.clear();
		spritePaths = soundPaths = musicPaths = null;
	}
}
