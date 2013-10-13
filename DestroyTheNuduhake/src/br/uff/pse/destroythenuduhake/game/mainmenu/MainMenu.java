package br.uff.pse.destroythenuduhake.game.mainmenu;

import java.util.ArrayList;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Game;
import br.uff.pse.destroythenuduhake.game.control.Level;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.control.Text;
import br.uff.pse.destroythenuduhake.game.control.TextListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenu extends Level implements TextListener{

	private Text title, startText, editText, dtnText;
	private LevelObject bg;
	private MusicAsset music;
	
	@Override
	public void createWithAssetBundle(AssetBundle bundle){
		super.createWithAssetBundle(bundle);
		
		BitmapFont f = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		
		float middle = Gdx.graphics.getWidth() / 2f;
		
		bg = new LevelObject(0, 0, bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_DRAGON));
		addActor(bg);
		
		title = new Text(f, "Destroy The Nuduhake", middle, 350);
		title.setScale(2.0f);
		
		startText = new Text(0, f, "Start", middle, 225, this);
		editText = new Text(1, f, "Edit Assets", middle, 150, this);
		dtnText = new Text(2, f, "Config DTN", middle, 80, this);
		addActor(title);
		addActor(startText);
		addActor(editText);
		addActor(dtnText);
		
		music = bundle.<MusicAsset>getAsset(AssetDatabase.MUSIC_OPENING);
		music.play();
	}	
	
//	@Override
//	public void act(float delta) {
//		// TODO Auto-generated method stub
//		super.act(delta);
//		getParent().changeLevel(1);
//	}
	
	public void startGame(){
		getParent().openBundleAssembler();

	}
	
	public void editAssets(){
//		Gdx.app.log("", "edit assets");
		getParent().openAssetModule();
	}
	
	public void doDTN(){
//		Gdx.app.log("", "do dtn");
		getParent().openDTNModule();
	}

	@Override
	public void onTouched(int id) {
		switch(id){
		case 0:
			startGame();
			break;
		case 1:
			editAssets();
			break;
		case 2:
			doDTN();
			break;
		}
			
		
	}
	
}
