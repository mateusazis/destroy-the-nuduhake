package br.uff.pse.destroythenuduhake.game;

import java.util.ArrayList;

import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenu extends Level implements TextListener{

	private Text title, startText, editText, dtnText;
	private LevelObject bg;
	
	@Override
	public void createWithAssetBundle(AssetBundle bundle){
		super.createWithAssetBundle(bundle);
		
		BitmapFont f = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		
		float middle = Gdx.graphics.getWidth() / 2f;
		
		bg = new LevelObject(0, 0, bundle.<GraphicAsset>getAsset(AssetIDs.SPRITE_DRAGON));
		addActor(bg);
		
		title = new Text(f, "Destroy The Nuduhake", middle, 350);
		startText = new Text(0, f, "Start", middle, 200, this);
		editText = new Text(1, f, "Edit Assets", middle, 150, this);
		dtnText = new Text(2, f, "Config DTN", middle, 100, this);
		addActor(title);
		addActor(startText);
		addActor(editText);
		addActor(dtnText);
		
		
	}	
	
	public void startGame(){
		Game parent = getParent();
		parent.changeLevel(parent.getCurrentLevel() + 1);
	}
	
	public void editAssets(){
		Gdx.app.log("", "edit assets");
		
	}
	
	public void doDTN(){
		Gdx.app.log("", "do dtn");
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
