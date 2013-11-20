package br.uff.pse.destroythenuduhake.game.mainmenu;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MainMenu extends Level implements TextListener{

	private static final boolean SKIP_INTRO = false;
	private Text title, startText, editText;
	private LevelObject bg;
	private MusicAsset music;
	private BitmapFont f;
	private Pixmap p;
	private Texture fadeTexture;
	
	private float elapsed = 0;
	private static final float FADE_IN_DURATION = 3.2f;
	
	public MainMenu(){
		super();
		setRequiredAssets(AssetDatabase.SPRITE_DRAGON, AssetDatabase.MUSIC_OPENING);
	}
	
	@Override
	public void createWithAssetBundle(AssetBundle bundle){
		super.createWithAssetBundle(bundle);
		elapsed = 0;
		
		f = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), false);
		
		float middle = 400f;
		
		bg = new LevelObject(0, 0, bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_DRAGON));
		addActor(bg);
		
		title = new Text(f, "Destroy The Nuduhake", middle, 350);
		title.setScale(2.0f);
		title.setTouchable(Touchable.disabled);
		
		startText = new Text(0, f, "Start", middle, 150, this);
		editText = new Text(1, f, "Edit Assets", middle, 80, this);
		addActor(title);
		addActor(startText);
		addActor(editText);
		
		music = bundle.<MusicAsset>getAsset(AssetDatabase.MUSIC_OPENING);
		music.play();
		
		p = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Format.RGBA4444);
		fadeTexture = new Texture(p);
		p.setColor(0, 0, 0, 1);
		p.fill();
		fadeTexture.draw(p, 0, 0);
	}	
	
	@Override
	public void dispose() {
		super.dispose();
		f.dispose();
		fadeTexture.dispose();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(elapsed < FADE_IN_DURATION){
			elapsed += delta;
		}
		
		if(SKIP_INTRO)
			getParent().changeLevel(1);
		
	}
	
	public void startGame(){
		getParent().openBundleAssembler();

	}
	
	public void editAssets(){
		getParent().openAssetModule();
	}
	
	public void doDTN(){
		getParent().openDTNModule();
	}

	
	@Override
	public void draw() {
		super.draw();
		
		if(elapsed < FADE_IN_DURATION){
			SpriteBatch batch = getSpriteBatch();
			batch.begin();
			batch.setColor(0, 0, 0, 1 - elapsed / FADE_IN_DURATION);
			batch.draw(fadeTexture, 0, 0);
			batch.end();
			batch.setColor(1,1,1,1);
		}
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
