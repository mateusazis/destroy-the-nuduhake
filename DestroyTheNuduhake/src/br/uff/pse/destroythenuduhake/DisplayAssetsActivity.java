package br.uff.pse.destroythenuduhake;



import java.util.ArrayList;
import java.util.List;


import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter;
import br.uff.pse.files.FileManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class DisplayAssetsActivity extends Activity
{



	private ListView listView;
	private List<Item> values;	

	
	private OnItemClickListener listener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			try
			{
			((ListItem)(values.get(arg2))).setChecked(true);
			Asset a = ((ListItem)(values.get(arg2))).getAsset();
			for(int i = 0 ; i < values.size();i++)
			{
				if(values.get(i) instanceof ListItem)
				{
					if(a.getId() == ((ListItem)values.get(i)).getAsset().getId() && arg2!=i)
					{
						((ListItem)values.get(i)).setChecked(false);
					}
					
					
				}
			}
		//	FileManager.setAssetChecked(FileManager.readAsset(((ListItem)(values.get(arg2))).getFilepath(), DisplayAssetsActivity.this));
		//	TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(DisplayAssetsActivity.this, values);
	    //    listView.setAdapter(adapter);
			//Intent intent = new Intent(DisplayActivity.this, ShowContentActivity.class);
			//intent.putExtra("filepath", filepath);
			//startActivity(intent);

			}
			catch(Exception e)
			{
				
			}

		}
		
	};
	private OnItemLongClickListener llistener = new OnItemLongClickListener()
	{

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			try
			{
			// TODO Auto-generated method stub
				String filepath = ((ListItem)(values.get(arg2))).getAsset().getDataFilePath();
				Intent intent = new Intent(DisplayAssetsActivity.this, ShowAssetActivity.class);
				intent.putExtra("filepath", filepath);
				startActivity(intent);

			}
			catch(Exception e)
			{
				
			}
			return false;
		}
		
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.displayassetslayout);
		listView = (ListView)findViewById(R.id.listView1);				
		listView.setOnItemClickListener(listener);
		listView.setOnItemLongClickListener(llistener);
		showContents();
		
		Button b = (Button) findViewById(R.id.sendSelected);
		b.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{	
				AssetBundle bundle = new AssetBundle();
				for(int i = 0; i < values.size(); i++)
				{
					if(values.get(i) instanceof ListItem)
					{
						if(((ListItem)values.get(i)).getChecked())
						{
							bundle.addAsset(((ListItem)values.get(i)).getAsset());
						}
					}
				}
			}
		});
		
		
		
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
			
			try
			{
				values = FileManager.readAllFilesNames(this);
		        TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(this, values);
		        listView.setAdapter(adapter);
			}
			catch (Exception e)
			{

			}

			
			
		}
		catch (Exception e)
		{

		}
	}

}
