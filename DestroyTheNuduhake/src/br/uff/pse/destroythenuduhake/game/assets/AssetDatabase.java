package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

public final class AssetDatabase {
	
	private static Asset[] privateAssets, editableAssets;
	
	//sprites
	public static final AssetID
		SPRITE_MARIO = new AssetID(0x0000, "Mario", "TESTE"),
		SPRITE_SHELL = new AssetID(0x0001, "Shell", "TESTE"),
		SPRITE_GROUND = new AssetID(0x0002, "Ground", "TESTE"),
		SPRITE_DRAGON = new AssetID(0x0003, "Dragon", "TESTE"),
		SPRITE_BALL_SHOOTER_3 = new AssetID(0x0004, "Ball Shooter 3", "TESTE"),
		SPRITE_BALL = new AssetID(0x0005, "Ball", "TESTE"),
		SPRITE_SMOKE = new AssetID(0x0006, "Smoke", "TESTE"),
		SPRITE_BULLET = new AssetID(0x0007, "Bullet", "TESTE"),
		SPRITE_SHOOTER = new AssetID(0x0008, "Shooter", "TESTE"),
		SPRITE_SWORD = new AssetID(0x0009, "Sword", "TESTE"),
		
		//input
		SPRITE_INPUT_SLIDER = new AssetID(0x000A, "Input Slider", "TESTE"),
		SPRITE_INPUT_SLIDER_PRESSED = new AssetID(0x000B, "Input Slider Pressed", "TESTE"),
		SPRITE_INPUT_ATTACK = new AssetID(0x000C, "Input Attack", "TESTE"),
		SPRITE_INPUT_ATTACK_PRESSED = new AssetID(0x000D, "Input Attack Pressed", "TESTE"),
		SPRITE_INPUT_JUMP = new AssetID(0x000E, "Input Jump", "TESTE"),
		SPRITE_INPUT_JUMP_PRESSED = new AssetID(0x000F, "Input Jump Pressed", "TESTE"),
		
		SPRITE_BACKGROUND = new AssetID(0x0010, "Background", "TESTE"),
		SPRITE_BALL_SHOOTER_2 = new AssetID(0x0011, "Ball Shooter 2", "TESTE"),
		SPRITE_BALL_SHOOTER_1 = new AssetID(0x0012, "Ball Shooter 1", "TESTE"),
		
		SPRITE_HEART_FULL = new AssetID(0x0013, "Heart full", "TESTE"),
		SPRITE_HEART_HALF = new AssetID(0x0014, "Heart half", "TESTE"),
		SPRITE_HEART_EMPTY = new AssetID(0x0015, "Heart empty", "TESTE"),
		
		SPRITE_COIN = new AssetID(0x0016, "Coin", "TESTE"),
		SPRITE_COIN_SHINE = new AssetID(0x0017, "Coin Shine", "TESTE"),
		
		//GUI
		SPRITE_COIN_BG = new AssetID(0x0018, "Coin BG", "TESTE"),
		SPRITE_HEART_BG = new AssetID(0x0019, "Heart BG", "TESTE"),
		
		
	
	//musics
	MUSIC_OPENING = new AssetID(0x0200, "Opening BGM", ""),
	MUSIC_LEVEL = new AssetID(0x0201, "Level BGM", "");
	

//	public static AssetID[] getAllIDs(){
//		return new AssetID[]{
//				SPRITE_MARIO, SPRITE_SHELL, SPRITE_GROUND, SPRITE_DRAGON, SPRITE_BALL, SPRITE_BALL_SHOOTER,
//				SPRITE_SMOKE, SPRITE_SWORD,
//				
//				MUSIC_OPENING, SPRITE_BULLET, SPRITE_SHOOTER, 
//		};
//	}
	public static Asset[] getPrivateBuiltinAssets(){
		if(privateAssets == null)
			privateAssets =  new Asset[]{
				new GraphicAsset(AssetDatabase.SPRITE_SMOKE, "images/smoke1.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_SLIDER, "images/Input_slider.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_SLIDER_PRESSED, "images/Input_slider_pressed.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_JUMP, "images/jump_icon.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_JUMP_PRESSED, "images/jump_icon_pressed.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_ATTACK, "images/sword_icon.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INPUT_ATTACK_PRESSED, "images/sword_icon_pressed.png"),
				
				new GraphicAsset(AssetDatabase.SPRITE_HEART_FULL, "images/heart_full.png"),
				new GraphicAsset(AssetDatabase.SPRITE_HEART_HALF, "images/heart_half.png"),
				new GraphicAsset(AssetDatabase.SPRITE_HEART_EMPTY, "images/heart_empty.png"),
				new GraphicAsset(AssetDatabase.SPRITE_COIN_SHINE, "images/shine.png"),
				
				new GraphicAsset(AssetDatabase.SPRITE_COIN_BG, "images/coin_bg.png"),
				new GraphicAsset(AssetDatabase.SPRITE_HEART_BG, "images/heart_bg.png"),
				
				new MusicAsset(AssetDatabase.MUSIC_OPENING, "musics/opening.mp3"),
			};
		return privateAssets;
	}
	
	public static Asset[] getEditableBuiltinAssets(){
		if(editableAssets == null)
			editableAssets =  new Asset[]{
				new GraphicAsset(AssetDatabase.SPRITE_MARIO, "images/mario.png"),
				new GraphicAsset(AssetDatabase.SPRITE_SHELL, "images/shell.png"),
				new GraphicAsset(AssetDatabase.SPRITE_GROUND, "images/ground.png"),
				new GraphicAsset(AssetDatabase.SPRITE_DRAGON, "images/dragon.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL, "images/ball.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_3, "images/ball_shooter_3.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BULLET, "images/bullet.png"),
				new GraphicAsset(AssetDatabase.SPRITE_SHOOTER, "images/shooter.png"),
				new GraphicAsset(AssetDatabase.SPRITE_SWORD, "images/sword.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BACKGROUND, "images/game_background.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_2, "images/ball_shooter_2.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_1, "images/ball_shooter_1.png"),
				new GraphicAsset(AssetDatabase.SPRITE_COIN, "images/coin.png"),
				
				new MusicAsset(AssetDatabase.MUSIC_LEVEL, "musics/5.Never Back Down.mp3"),
			};
		return editableAssets;
	}
}