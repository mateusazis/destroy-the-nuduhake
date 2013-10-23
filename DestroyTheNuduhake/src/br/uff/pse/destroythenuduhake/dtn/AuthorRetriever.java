package br.uff.pse.destroythenuduhake.dtn;

import br.uff.pse.destroythenuduhake.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AuthorRetriever {

	private static final String PLAYER_NAME_PREF = "player_name";
	private static String storedName;
	private static boolean firstTimePlaying = false;
	
	private static boolean retrieveSavedName(Activity a){
		SharedPreferences prefs = a.getPreferences(Activity.MODE_PRIVATE);
		boolean contains = prefs.contains(PLAYER_NAME_PREF); 
		if(contains)
			storedName = prefs.getString(PLAYER_NAME_PREF, "NO_PLAYER_NAME");
		return contains;
	}
	
	private static void retrieveNewName(final Activity a){
		AlertDialog.Builder b = new AlertDialog.Builder(a);
		LayoutInflater inf = a.getLayoutInflater();
		View v = inf.inflate(R.layout.dialog_signin, null);
		final EditText nameField = (EditText)v.findViewById(R.id.username);
		
		String msg = "É a primeira vez que você usa o Destroy The Nuduhake. Por favor, informe um nome para identificar seus assets.";
		
		b.setTitle("Bem-vindo").
		setMessage(msg).setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				storedName = nameField.getText().toString();
				
				SharedPreferences prefs = a.getPreferences(Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(PLAYER_NAME_PREF, storedName);
				editor.commit();
				
				Toast.makeText(a, "Seja bem-vindo, " + storedName + ".", Toast.LENGTH_SHORT).show();
			}
		});
		b.create().show();
	}
	
	public static void initialize(Activity a){
		if(!retrieveSavedName(a)){
			retrieveNewName(a);
			firstTimePlaying = true;
		} else
			firstTimePlaying = false;
	}
	
	public static boolean isFirstTimePlaying(){
		return firstTimePlaying;
	}
	
	public static String getAuthorName(){
		return storedName;
	}
}
