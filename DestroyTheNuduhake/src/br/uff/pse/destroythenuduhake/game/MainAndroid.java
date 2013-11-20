package br.uff.pse.destroythenuduhake.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import br.uff.pse.destroythenuduhake.dtn.AuthorRetriever;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Game;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainAndroid extends AndroidApplication {
	
	private static final int CODE_GET_ASSET_BUNDLE = 0x200;
	public static AssetBundle chosenBundle;
	
	public static MainAndroid instance;
	private AndroidApplicationConfiguration cfg;
	private Game g;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     FileManager.setContext(this);
        Configs.setup(true);
        
       
        
        instance = this;
        cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        AssetBundle b = new TestBundle();
        g = new Game(b);
        initialize(g, cfg);
        
        AuthorRetriever.initialize(this);
//       if(AuthorRetriever.isFirstTimePlaying())
//        	FileManager.saveBuiltInAssets(this);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && g.getCurrentLevel() != 0){
    		g.changeLevel(0);
    		return false;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	g.dispose();
    	instance = null;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	log("", "request code: " + requestCode + " result code " + resultCode);
    	if(requestCode == CODE_GET_ASSET_BUNDLE && resultCode == RESULT_OK){
    		for(Asset a : AssetDatabase.getPrivateBuiltinAssets())
    			chosenBundle.addAsset(a);
    		g.startGame(chosenBundle);
    		chosenBundle = null;
    	}
    }
    
    public void openBundleAssembler(){
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.DisplayAssetsActivity.class;
    	Intent i = new Intent(this, c);
    	startActivityForResult(i, CODE_GET_ASSET_BUNDLE);
    }
    
    public void showCredits(){
    	//TROCAR AQUI PELA ACTIVITY DOS CRÉDITOS
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.CreditsActivity.class;
    	Intent i = new Intent(this, c);
    	startActivity(i);
    }
    
    public void openDrawModule(){
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.AssetsWorkshopActivity.class;
    	Intent i = new Intent(this, c);
    	startActivity(i);
    }
}
