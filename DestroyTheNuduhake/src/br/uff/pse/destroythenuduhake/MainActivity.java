package br.uff.pse.destroythenuduhake;


//V de viadÃ£o
import br.uff.pse.files.Asset;
import br.uff.pse.files.FileManager;
import android.os.Bundle;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tx = (TextView)findViewById(R.id.textView1);
		
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

		Asset asset = new Asset("TESTANDO ALOW ALOW! RIAIRAIRIARA");
		FileManager.writeAsset(asset, "teste", this);
		FileManager.deleteAsset("teste", this);
		Asset asset2 = FileManager.readAsset("teste", this);
		if(asset2 != null)
			tx.setText(asset2.teste);
		else
		{
			tx.setText("Asset null");
		}
		
		
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
