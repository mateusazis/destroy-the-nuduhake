package br.uff.pse.destroythenuduhake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {

	boolean loadDTN = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Class<? extends Activity> c;
		if(loadDTN)
			c = MainActivity.class;
		else
			c = br.uff.pse.destroythenuduhake.game.Main.class;
		
		Intent i = new Intent(this, c);
		startActivity(i);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("", "restart");
		finish();
	}
	
}
