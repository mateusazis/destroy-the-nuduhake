package br.uff.pse.destroythenuduhake.game.control;

import br.uff.pse.destroythenuduhake.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AuthorRetriever {

	private static final String PLAYER_NAME_PREF = "player_name", PLAYER_UID_PREF = "player_uid";
	private static Author storedAuthor;
	
	private static boolean retrieveSavedName(Activity a){
		SharedPreferences prefs = a.getPreferences(Activity.MODE_PRIVATE);
		boolean contains = prefs.contains(PLAYER_NAME_PREF); 
		if(contains){
			String name = prefs.getString(PLAYER_NAME_PREF, "NO_PLAYER_NAME");
			String uid = prefs.getString(PLAYER_UID_PREF, "NO_PLAYER_UID");
			storedAuthor = new Author(name, uid);
		}
		return contains;
	}
	
	private static void retrieveNewName(final Activity a){
		AlertDialog.Builder b = new AlertDialog.Builder(a);
		LayoutInflater inf = a.getLayoutInflater();
		View v = inf.inflate(R.layout.dialog_signin, null);
		final EditText nameField = (EditText)v.findViewById(R.id.username);
		
		String msg = a.getResources().getString(R.string.first_use_msg);
		
		b.setTitle("Bem-vindo").
		setMessage(msg).setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = nameField.getText().toString();
				String uid = Settings.Secure.ANDROID_ID;
				storedAuthor = new Author(name, uid);
				
				SharedPreferences prefs = a.getPreferences(Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(PLAYER_NAME_PREF, name);
				editor.putString(PLAYER_UID_PREF, uid);
				editor.commit();
				
				Toast.makeText(a, "Seja bem-vindo, " + name + ".", Toast.LENGTH_SHORT).show();
			}
		});
		b.create().show();
	}
	
	public static void initialize(Activity a){
		if(!retrieveSavedName(a))
			retrieveNewName(a);
	}
	
	public static Author getAuthor(){
		return storedAuthor;
	}
}
