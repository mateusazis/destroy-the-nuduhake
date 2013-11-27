package br.uff.pse.destroythenuduhake;


//V de viadÃƒÂ£o

import br.uff.pse.destroythenuduhake.dtn.DTNService;
import br.uff.pse.files.FileManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
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
		//BOTÃ¯Â¿Â½O TESTE
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

			}
		});
		Button b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{		
				//sendTestDtnBundle();
				FileManager.deleteAllFiles(MainActivity.this);
			}
		});
		Button b4 = (Button) findViewById(R.id.assetWorkshop);
		b4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
					//sendTestDtnBundle();
					try
					{
						Intent intent = new Intent(MainActivity.this, AssetsWorkshopActivity.class);
						startActivity(intent);
					}
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
	}

	public void alertServiceToSend()
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
