package br.uff.pse.destroythenuduhake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class ShowAssetActivity extends Activity
{
	ImageView imgView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.showgraphicassetlayout);
		imgView = (ImageView)findViewById(R.id.imageView1);		
//		Intent intent = getIntent();
//		String filepath = intent.getStringExtra("filepath");
		try
		{			
			GraphicAsset a = (GraphicAsset) DisplayAssetsActivity.getAsset();
			imgView.setImageBitmap(a.getBitmap(ShowAssetActivity.this));
		}
		catch(Exception e)
		{
//			Exception x = e;
		}


		
		
		
	}

}
