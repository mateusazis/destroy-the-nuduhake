package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

public final class AssetDatabase {
	
	//sprites
	public static final AssetID SPRITE_MARIO = new AssetID(0x0000, "Mario", "");
	public static final AssetID SPRITE_SHELL = new AssetID(0x0001, "Shell", "");
	public static final AssetID SPRITE_GROUND = new AssetID(0x0002, "Ground", "");
	public static final AssetID SPRITE_DRAGON = new AssetID(0x0003, "Dragon", "");
	public static final AssetID SPRITE_BALL_SHOOTER = new AssetID(0x0004, "BallShooter", "");
	public static final AssetID SPRITE_BALL = new AssetID(0x0005, "Ball", "");
	
	
	//musics
	public static final AssetID MUSIC_OPENING = new AssetID(0x0200, "Opening BGM", "");

	public static AssetID[] getAllIDs(){
		return new AssetID[]{
				SPRITE_MARIO, SPRITE_SHELL, SPRITE_GROUND, SPRITE_DRAGON, SPRITE_BALL, SPRITE_BALL_SHOOTER,
				
				MUSIC_OPENING,
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
			
			new MusicAsset(AssetDatabase.MUSIC_OPENING, "musics/opening.mp3"),
		};
	}
}