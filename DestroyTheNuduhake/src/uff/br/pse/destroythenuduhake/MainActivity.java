package uff.br.pse.destroythenuduhake;


//V de viadão
//TESTANDO O COMMIT UOOOOOOOW IGNOREM ESTE COMMENT!
import android.os.Bundle;

import android.app.Activity;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//BOT�O TESTE
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
					sendTestDtnBundle();
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
