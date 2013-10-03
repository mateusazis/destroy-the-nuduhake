package br.uff.pse.destroythenuduhake.game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainAndroid extends AndroidApplication {
	
	private AndroidApplicationConfiguration cfg;
	private Game g;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        g = new Game(DefaultBundle.getInstance());
        initialize(g, cfg);
//        initialize(new MyGdxGame(), cfg);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	showDialog(0);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	showDialog(0);
    	return false;
    	
    }
    
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
    	AlertDialog.Builder b = new AlertDialog.Builder(this);
    	
    	b.setTitle("Escolha um bundle").setItems(new String[]{"Padrão", "Teste"}, new DialogInterface.OnClickListener() {
			
    		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AssetBundle b = which == 0 ? DefaultBundle.getInstance() : new TestBundle();
				g.setUsedBundle(b);
				g.changeLevel(0);
			}
		});
    	
    	return b.show();
    }
}