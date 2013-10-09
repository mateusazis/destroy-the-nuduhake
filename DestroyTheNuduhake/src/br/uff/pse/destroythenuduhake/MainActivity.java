package br.uff.pse.destroythenuduhake;


//V de viadÃ£o

import br.uff.pse.destroythenuduhake.dtn.DTNService;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.files.FileManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tx;
	ImageView imview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imview = (ImageView) findViewById(R.id.imageView1);
		//BOTï¿½O TESTE
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
					//sendTestDtnBundle();
					try
					{
						testFileManager();
					}
					catch (NameNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
				//Asset um = new Asset("Capacete1","Capacete","Jurema");
				//Asset dois = new Asset("Ombreira2","Ombreira","Z�");
				//Asset tres = new Asset("Terreno Boladex","Terreno","Dieguin");
				//FileManager.writeAsset(um, um.name, MainActivity.this);
				//FileManager.writeAsset(dois, dois.name, MainActivity.this);
				GraphicAsset kibe = new GraphicAsset(0,getFilesDir() + "/teste","Autor Bolado",true);
				kibe.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dtnpacket));
				
				//FileManager.writeAsset(kibe,  MainActivity.this);
				byte[] b = FileManager.prepareContentToSend(kibe);
		
				FileManager.writeAssetFromBytes(b, MainActivity.this);
				GraphicAsset chapoca = (GraphicAsset) FileManager.getAssetFromBytes(b, MainActivity.this);
				imview.setImageBitmap(chapoca.getBitmap());
			}
		});
		Button b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
				FileManager.deleteAllFiles(MainActivity.this);
			}
		});
	}

	public void sendTestDtnBundle()
	{
		try
		{

			Intent i = new Intent(this, DTNService.class);
			i.setAction(DTNService.SEND_BUNDLE_INTENT);
			startService(i);
			i = new Intent(this, DTNService.class);

		}
		catch (Exception e)
		{

		}
	
	}
	public void testFileManager() throws NameNotFoundException
	{
		
		Intent intent = new Intent(MainActivity.this, DisplayAssetsActivity.class);
		startActivity(intent);


		
		
	}
	
	

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();

	}


}
