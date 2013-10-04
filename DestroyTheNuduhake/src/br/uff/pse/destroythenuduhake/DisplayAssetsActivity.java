package br.uff.pse.destroythenuduhake;



import java.util.ArrayList;
import java.util.List;

import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter;
import br.uff.pse.files.FileManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class DisplayAssetsActivity extends Activity implements OnItemClickListener
{


	private ListView listView;
	private String[] values;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.displayassetslayout);
		Intent intent = getIntent();
		String payload = intent.getStringExtra("payload");
		listView = (ListView)findViewById(R.id.listView1);
		showContents();
		
		
		
		
	}
	private void showContents()
	{
		// ImageView icon = (ImageView)findViewById(R.id.icon);
		try
		{
			
	//		values = FileManager.readAllFilesNames(this);
//			String[] values = {"Categoria","teste1","teste2","Categoria","teste3","Categoria","teste1","teste2","Categoria","teste3"};
			
			// Use your own layout
			// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			// R.layout.displayactivitymenu, R.id.label, values);

//			DisplayAdapter adapter = new DisplayAdapter(R.layout.rowlayout,values,this);
//			listView.setAdapter(adapter);
//			listView.setOnItemClickListener(this);
			
			
			List<Item> items = FileManager.readAllFilesNames(this);


	        TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(this, items);
	        listView.setAdapter(adapter);
			
			
		}
		catch (Exception e)
		{

		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) 
	{
		
		
	}

}
