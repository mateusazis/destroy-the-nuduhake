package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

public final class AssetDatabase {
	
	//sprites
	public static final AssetID
		SPRITE_MARIO = new AssetID(0x0000, "Mario", ""),
		SPRITE_SHELL = new AssetID(0x0001, "Shell", ""),
		SPRITE_GROUND = new AssetID(0x0002, "Ground", ""),
		SPRITE_DRAGON = new AssetID(0x0003, "Dragon", ""),
		SPRITE_BALL_SHOOTER = new AssetID(0x0004, "Ball Shooter", ""),
		SPRITE_BALL = new AssetID(0x0005, "Ball", ""),
		SPRITE_SMOKE = new AssetID(0x0006, "Smoke", ""),
		SPRITE_BULLET = new AssetID(0x0007, "Bullet", ""),
		SPRITE_SHOOTER = new AssetID(0x0008, "Shooter", ""),
		SPRITE_SWORD = new AssetID(0x0009, "Sword", ""),
	
	//musics
	MUSIC_OPENING = new AssetID(0x0200, "Opening BGM", "");
	

	public static AssetID[] getAllIDs(){
		return new AssetID[]{
				SPRITE_MARIO, SPRITE_SHELL, SPRITE_GROUND, SPRITE_DRAGON, SPRITE_BALL, SPRITE_BALL_SHOOTER,
				SPRITE_SMOKE, SPRITE_SWORD,
				
				MUSIC_OPENING, SPRITE_BULLET, SPRITE_SHOOTER, 
		};
	}
	
	
	public static Asset[] getOriginalAssets(){
		return new Asset[]{
			new GraphicAsset(AssetDatabase.SPRITE_MARIO, "images/mario.png"),
			new GraphicAsset(AssetDatabase.SPRITE_SHELL, "images/shell.png"),
			new GraphicAsset(AssetDatabase.SPRITE_GROUND, "images/ground.png"),
			new GraphicAsset(AssetDatabase.SPRITE_DRAGON, "images/dragon.png"),
			new GraphicAsset(AssetDatabase.SPRITE_BALL, "images/ball.png"),
			new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER, "images/ball_shooter.png"),
			new GraphicAsset(AssetDatabase.SPRITE_SMOKE, "images/smoke1.png"),
			new GraphicAsset(AssetDatabase.SPRITE_BULLET, "images/bullet.png"),
			new GraphicAsset(AssetDatabase.SPRITE_SHOOTER, "images/shooter.png"),
			new GraphicAsset(AssetDatabase.SPRITE_SWORD, "images/sword.png"),
			
			new MusicAsset(AssetDatabase.MUSIC_OPENING, "musics/opening.mp3"),
		};
	}
}