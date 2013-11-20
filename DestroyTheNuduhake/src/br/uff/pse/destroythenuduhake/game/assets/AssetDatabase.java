package br.uff.pse.destroythenuduhake.game.assets;

import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetID;

public final class AssetDatabase {
	
	private static Asset[] privateAssets, editableAssets;
	
	//sprites
	public static final AssetID
		SPRITE_MARIO = new AssetID(0x0000, "Ngilangi", "O grande guerreiro que deve enfrentar Nuduhake!"),
		SPRITE_DRAGON = new AssetID(0x0003, "Dragon", "TESTE"),
		SPRITE_BALL_SHOOTER_3 = new AssetID(0x0004, "Torre atiradora 3", "Atira bolas perigosas em você!"),
		SPRITE_BALL = new AssetID(0x0005, "Bola explosiva", "Atirado pelas torres. Explodem quando entram em contato. Dizem que também captura Pokémons."),
		SPRITE_SMOKE = new AssetID(0x0006, "Smoke", "TESTE"),
		SPRITE_BULLET = new AssetID(0x0007, "Tiro", "Acertou, tá morto! ... ou pelo menos perde umas vidas..."),
		SPRITE_SHOOTER = new AssetID(0x0008, "Atirador", "Atirador impiedoso que você nunca viu antes em outro jogo."),
		SPRITE_SWORD = new AssetID(0x0009, "Nudukira", "Espada mágica montada nas forjas infernais da 25 de Março."),
		
		//input
		SPRITE_INPUT_SLIDER = new AssetID(0x000A, "Input Slider", "TESTE"),
		SPRITE_INPUT_SLIDER_PRESSED = new AssetID(0x000B, "Input Slider Pressed", "TESTE"),
		SPRITE_INPUT_ATTACK = new AssetID(0x000C, "Input Attack", "TESTE"),
		SPRITE_INPUT_ATTACK_PRESSED = new AssetID(0x000D, "Input Attack Pressed", "TESTE"),
		SPRITE_INPUT_JUMP = new AssetID(0x000E, "Input Jump", "TESTE"),
		SPRITE_INPUT_JUMP_PRESSED = new AssetID(0x000F, "Input Jump Pressed", "TESTE"),
		
		SPRITE_BACKGROUND = new AssetID(0x0010, "Floresta javanesa", "Lá se faz café. E apps Android."),
		SPRITE_BALL_SHOOTER_2 = new AssetID(0x0011, "Torre atiradora 2", "Torre que atira mais baixo!"),
		SPRITE_BALL_SHOOTER_1 = new AssetID(0x0012, "Torre atiradora 1", "Restinho de torre que ainda atira!"),
		
		SPRITE_HEART_FULL = new AssetID(0x0013, "Heart full", "TESTE"),
		SPRITE_HEART_HALF = new AssetID(0x0014, "Heart half", "TESTE"),
		SPRITE_HEART_EMPTY = new AssetID(0x0015, "Heart empty", "TESTE"),
		
		SPRITE_COIN = new AssetID(0x0016, "Moeda", "Vale dinheiro."),
		SPRITE_COIN_SHINE = new AssetID(0x0017, "Coin Shine", "TESTE"),
		
		//GUI
		SPRITE_COIN_BG = new AssetID(0x0018, "Coin BG", "TESTE"),
		SPRITE_HEART_BG = new AssetID(0x0019, "Heart BG", "TESTE"),
		
		SPRITE_INTRO_BAR_TOP = new AssetID(0x001A, "Intro bar top", "TESTE"),
		SPRITE_INTRO_BAR_MIDDLE = new AssetID(0x001B, "Intro bar middle", "TESTE"),
		SPRITE_INTRO_BAR_BOTTOM = new AssetID(0x001C, "Intro bar bottom", "TESTE"),
		
		SPRITE_IN_GAME_HEART = new AssetID(0x001D, "Coração", "Recupera sua vida. Mas você não vai precisar disso, né?"),
		
		
	
	//musics
	MUSIC_OPENING = new AssetID(0x0200, "Opening BGM", "Música de abertura"),
	MUSIC_LEVEL = new AssetID(0x0201, "Level BGM", "Música da fase 1"),
	
	//sounds
	SOUND_SWORD = new AssetID(0x0300, "Sword atack", ""),
	SOUND_BALL_EXPLOSION = new AssetID(0x0301, "Ball explosion", ""),
	SOUND_IMPACT = new AssetID(0x0302, "Impact", ""),
	SOUND_SHOT = new AssetID(0x0303, "Shot", ""),
	SOUND_JUMP = new AssetID(0x0304, "Jump", ""),
	SOUND_COIN = new AssetID(0x0305, "Coin", ""),
	SOUND_LAND = new AssetID(0x0306, "Land", ""),
	SOUND_BALL_SHOOTER_SHOOT = new AssetID(0x0307, "Ball Shooter Shoot", ""),
	SOUND_HEART = new AssetID(0x0308, "Heart", "");
	
	public static Asset[] getPrivateBuiltinAssets(){
		if(privateAssets == null)
			privateAssets =  new Asset[]{
				new GraphicAsset(AssetDatabase.SPRITE_SMOKE, "images/smoke1.png"),
				new GraphicAsset(AssetDatabase.SPRITE_DRAGON, "images/dragon.png"),
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
				
				new GraphicAsset(AssetDatabase.SPRITE_INTRO_BAR_TOP, "images/barra_cima.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INTRO_BAR_MIDDLE, "images/barra_meio.png"),
				new GraphicAsset(AssetDatabase.SPRITE_INTRO_BAR_BOTTOM, "images/barra_baixo.png"),
				
				new MusicAsset(AssetDatabase.MUSIC_OPENING, "musics/opening.mp3"),
				
				new SoundAsset(AssetDatabase.SOUND_SWORD, "sounds/sword.ogg"),
				new SoundAsset(AssetDatabase.SOUND_BALL_EXPLOSION, "sounds/ball_explosion.wav"),
				new SoundAsset(AssetDatabase.SOUND_IMPACT, "sounds/impact.wav"),
				new SoundAsset(AssetDatabase.SOUND_SHOT, "sounds/shot.wav"),
				new SoundAsset(AssetDatabase.SOUND_SHOT, "sounds/shot.wav"),
				new SoundAsset(AssetDatabase.SOUND_JUMP, "sounds/jump.wav"),
				new SoundAsset(AssetDatabase.SOUND_COIN, "sounds/coin.wav"),
				new SoundAsset(AssetDatabase.SOUND_LAND, "sounds/land.wav"),
				new SoundAsset(AssetDatabase.SOUND_BALL_SHOOTER_SHOOT, "sounds/ball_shooter_shoot.wav"),
				new SoundAsset(AssetDatabase.SOUND_HEART, "sounds/heart.wav"),
			};
		return privateAssets;
	}
	
	public static Asset[] getEditableBuiltinAssets(){
		if(editableAssets == null)
			editableAssets =  new Asset[]{
				new GraphicAsset(AssetDatabase.SPRITE_MARIO, "images/mario.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL, "images/ball.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_3, "images/ball_shooter_3.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BULLET, "images/bullet.png"),
				new GraphicAsset(AssetDatabase.SPRITE_SHOOTER, "images/shooter.png"),
				new GraphicAsset(AssetDatabase.SPRITE_SWORD, "images/sword.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BACKGROUND, "images/game_background.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_2, "images/ball_shooter_2.png"),
				new GraphicAsset(AssetDatabase.SPRITE_BALL_SHOOTER_1, "images/ball_shooter_1.png"),
				new GraphicAsset(AssetDatabase.SPRITE_COIN, "images/coin.png"),
				new GraphicAsset(AssetDatabase.SPRITE_IN_GAME_HEART, "images/heart_full.png"),
				
				new MusicAsset(AssetDatabase.MUSIC_LEVEL, "musics/5.Never Back Down.ogg"),
			};
		return editableAssets;
	}
}