package br.uff.pse.destroythenuduhake;



import java.util.List;



import br.uff.pse.destroythenuduhake.game.MainAndroid;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.AssetID;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter;
import br.uff.pse.files.FileManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class DisplayAssetsActivity extends Activity
{




	private ListView listView;
	private List<Item> values;
	private static Asset asset;

	
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
					AssetID x = a.getId();
					AssetID y = ((ListItem)values.get(i)).getAsset().getId();
					if(x.equals(y) && arg2!=i)
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
	
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
    		setResult(RESULT_CANCELED);
    		finish();
    		return false;
    	}
    	return super.onKeyDown(keyCode, event);
    }
	
	private OnItemLongClickListener llistener = new OnItemLongClickListener()
	{

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			try
			{
			// TODO Auto-generated method stub
				//String filepath = ((ListItem)(values.get(arg2))).getAsset().getDataFilePath();
				GraphicAsset ga = (GraphicAsset) ((ListItem)(values.get(arg2))).getAsset();
				setAsset(ga);
				Intent intent = new Intent(DisplayAssetsActivity.this, ShowAssetActivity.class);
				//intent.putExtra("filepath", filepath);
				startActivity(intent);

				
			}
			catch(Exception e)
			{
				
			}
			return false;
		}
		
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mDataReceiver);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		IntentFilter filter2 = new IntentFilter(FileManager.REFRESH);
		registerReceiver(mDataReceiver, filter2);
		setContentView(R.layout.displayassetslayout);
		listView = (ListView)findViewById(R.id.listView1);				
		listView.setOnItemClickListener(listener);
		listView.setOnItemLongClickListener(llistener);
		showContents();
		
		Button b = (Button) findViewById(R.id.button1);
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
				MainAndroid.chosenBundle = bundle;
				setResult(RESULT_OK);      
				finish();
			}
		});
		
		
		
	}
	private void showContents()
	{
		// ImageView icon = (ImageView)findViewById(R.id.icon);
		try
		{
			

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
	public static Asset getAsset() {
		return asset;
	}
	public static void setAsset(Asset asset) {
		DisplayAssetsActivity.asset = asset;
	}
	private BroadcastReceiver mDataReceiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			showContents();

		}
	};


}
