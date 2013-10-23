package br.uff.pse.destroythenuduhake.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import br.uff.pse.destroythenuduhake.dtn.AuthorRetriever;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Game;
import br.uff.pse.files.FileManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainAndroid extends AndroidApplication {
	
	public static final int CODE_GET_ASSET_BUNDLE = 0x200;
	public static AssetBundle chosenBundle;
	
	public static MainAndroid instance;
	private AndroidApplicationConfiguration cfg;
	private Game g;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configs.setup(true);
        FileManager.saveBuiltInAssets(this);
        instance = this;
        cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        AssetBundle b = new TestBundle();
        g = new Game(b);
        initialize(g, cfg);
        
        AuthorRetriever.initialize(this);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	instance = null;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	showDialog(0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	log("", "request code: " + requestCode + " result code " + resultCode);
    	if(requestCode == CODE_GET_ASSET_BUNDLE){
    		g.startGame(chosenBundle);
    		chosenBundle = null;
    	}
    }
    
    public void openBundleAssembler(){
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.DisplayAssetsActivity.class;
    	Intent i = new Intent(this, c);
    	startActivityForResult(i, CODE_GET_ASSET_BUNDLE);
    }
    
    public void openDTNModule(){
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.MainActivity.class;
    	Intent i = new Intent(this, c);
    	startActivity(i);
    }
    
    public void openDrawModule(){
    	Class<? extends Activity> c = br.uff.pse.destroythenuduhake.AssetsWorkshopActivity.class;
    	Intent i = new Intent(this, c);
    	startActivity(i);
    }
    
//    @Override
//    protected Dialog onCreateDialog(int id, Bundle args) {
//    	AlertDialog.Builder b = new AlertDialog.Builder(this);
//    	
//    	b.setTitle("Escolha um bundle").setItems(new String[]{"Padrão", "Teste"}, new DialogInterface.OnClickListener() {
//			
//    		
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				AssetBundle b = which == 0 ? DefaultBundle.getInstance() : new TestBundle();
//				g.setUsedBundle(b);
//				g.changeLevel(0);
//			}
//		});
//    	
//    	return b.show();
//    }
}