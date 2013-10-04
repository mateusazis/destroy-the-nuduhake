package br.uff.pse.destroythenuduhake;


//V de viadÃ£o

import java.io.File;

import br.uff.pse.destroythenuduhake.dtn.DTNService;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.files.FileManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tx;
	ImageView imteste;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imteste = (ImageView) findViewById(R.id.imageView1);
		
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
				Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.dtnpacket);
				GraphicAsset ass = new GraphicAsset(0);
				File path = getFilesDir();
				String filepath = path.getAbsolutePath();
				ass.filePath = filepath +  "/teste";
				ass.saveBitmap(b);
				GraphicAsset ass2 = (GraphicAsset) FileManager.readAsset(ass.filePath,MainActivity.this);
				Bitmap kibe = ass2.getBitmap();
				int x = kibe.getWidth();
				imteste.setImageBitmap(kibe);
				
				
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
