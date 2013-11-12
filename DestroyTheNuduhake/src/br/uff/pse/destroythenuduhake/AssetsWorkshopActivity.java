package br.uff.pse.destroythenuduhake;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;
import br.uff.pse.destroythenuduhake.drawing.FreehandDrawingActivity;
import br.uff.pse.destroythenuduhake.game.MainAndroid;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.AudioAsset;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.AssetID;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter;
import br.uff.pse.files.FileManager;

public class AssetsWorkshopActivity extends Activity
{


	
	private ListView listView;
	private List<Item> values;	
	public static GraphicAsset asset;
	private Dialog speakerDialog;
	private AlertDialog.Builder speakerBuilder;
	private Dialog  dialog;

	
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
				if(((ListItem)(values.get(arg2))).getAsset() instanceof GraphicAsset)
				{
					
					
					
					GraphicAsset ga =  (GraphicAsset) ((ListItem)(values.get(arg2))).getAsset();
					setAsset(ga);
					setImageBuilder(ga);
					dialog.show();
					
				}
				else
					if(((ListItem)(values.get(arg2))).getAsset() instanceof AudioAsset)
					{
						setSpeakerDialogBuilder();
						speakerDialog.show();
					}
			}
			catch(Exception e)
			{
				
				Exception x = e;
			}

		}
		
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		showContents();
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
	private void setSpeakerDialogBuilder()
	{
		speakerBuilder = new AlertDialog.Builder(this);
		speakerBuilder.setMessage("Destroy The Nuduhake does not support audio edition yet.");
		
		
		speakerDialog = speakerBuilder.create();
		
		
		
	}

	private void setImageBuilder(GraphicAsset ga)
	{
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.awdialog);
		dialog.setTitle("Asset Edition");
		
		
		
		TextView assetName = (TextView) dialog.findViewById(R.id.textView2);
		
        TextView text = (TextView) dialog.findViewById(R.id.textView1);
        text.setTypeface(null, Typeface.ITALIC);
        text.setText(ga.getId().getDescription());
//        text.setText("TESTE!");
        ImageView image = (ImageView) dialog.findViewById(R.id.imageView1);
        image.setImageBitmap(ga.getBitmap(AssetsWorkshopActivity.this));
        assetName.setText(ga.getId().getName());

        Button dialogButtonProceed = (Button) dialog.findViewById(R.id.goButton);
        dialogButtonProceed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(AssetsWorkshopActivity.this, FreehandDrawingActivity.class);
				startActivityForResult(intent, 0);
				dialog.dismiss();
            }
        });
	}


}
