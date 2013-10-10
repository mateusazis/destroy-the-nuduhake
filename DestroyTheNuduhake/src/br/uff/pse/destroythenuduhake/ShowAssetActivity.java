package br.uff.pse.destroythenuduhake;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;
import br.uff.pse.files.FileManager;

public class ShowAssetActivity extends Activity
{
	ImageView imgView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.showgraphicassetlayout);
		imgView = (ImageView)findViewById(R.id.imageView1);		
		Intent intent = getIntent();
		String filepath = intent.getStringExtra("filepath");
		try
		{			
			imgView.setImageBitmap(FileManager.getBitmapFromFilepath(filepath));
		}
		catch(Exception e)
		{
			Exception x = e;
		}


		
		
		
	}

}
