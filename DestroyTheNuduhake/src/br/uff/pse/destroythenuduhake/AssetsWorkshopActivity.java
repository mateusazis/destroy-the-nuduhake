package br.uff.pse.destroythenuduhake;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import br.uff.pse.destroythenuduhake.drawing.FreehandDrawingActivity;
import br.uff.pse.destroythenuduhake.game.MainAndroid;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter;
import br.uff.pse.files.FileManager;

public class AssetsWorkshopActivity extends Activity
{


	
	private ListView listView;
	private List<Item> values;	
	public static GraphicAsset asset;

	
	public static GraphicAsset getAsset() {
		return asset;
	}
	public static void setAsset(GraphicAsset asset) {
		AssetsWorkshopActivity.asset = asset;
	}
	private OnItemClickListener listener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			try
			{
				GraphicAsset ga =  (GraphicAsset) ((ListItem)(values.get(arg2))).getAsset();
				setAsset(ga);
				Intent intent = new Intent(AssetsWorkshopActivity.this, FreehandDrawingActivity.class);
				startActivity(intent);
			}
			catch(Exception e)
			{
				
			}

		}
		
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.assetsworkshop);
		listView = (ListView)findViewById(R.id.workshopList);				
		listView.setOnItemClickListener(listener);

		showContents();
		

		
		
		
	}
	
	private void showContents()
	{
		// ImageView icon = (ImageView)findViewById(R.id.icon);
		try
		{

			try
			{
				values = FileManager.readAllFilesNames(this);
				for(int i = 0;i<values.size();i++)
				{
					if(values.get(i) instanceof ListItem)
					{
						((ListItem)(values.get(i))).setCheckBoxInv();
					}
				}
		        TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(this, values);
		        listView.setAdapter(adapter);
			}
			catch (Exception e)
			{
				Exception x = e;
			}

			
			
		}
		catch (Exception e)
		{

		}
	}

}
