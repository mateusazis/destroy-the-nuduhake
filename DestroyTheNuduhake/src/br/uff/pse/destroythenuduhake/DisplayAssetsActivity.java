package br.uff.pse.destroythenuduhake;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


public class DisplayAssetsActivity extends Activity{

	private EditText editText;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.displayassetslayout);
		editText = (EditText)findViewById(R.id.editText1);
		Intent intent = getIntent();
		String payload = intent.getStringExtra("payload");
		editText.setText(payload);		
	}
}
