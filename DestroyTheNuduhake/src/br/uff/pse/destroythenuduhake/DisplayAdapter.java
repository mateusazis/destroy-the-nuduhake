package br.uff.pse.destroythenuduhake;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
//import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayAdapter extends ArrayAdapter<String>
{
	private int layoutID;
	private final Context context;
	private final String[] values;

	public DisplayAdapter(int listViewId, String[] values,Context ctx)
	{
		super(ctx, listViewId, values);
		this.layoutID = listViewId;
		this.values = values;
		this.context = ctx;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;

		if(rowView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
        	rowView = inflater.inflate(layoutID, parent, false);
		}
        
		

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		textView.setText(values[position]);


//		imageView.setImageResource(R.drawable.square);
//		imageView.setAlpha(1);

//		float percent = (float) position / (float) values.length;
//		imageView.setBackgroundColor((int) interpolateColor(Color.GREEN, Color.RED, percent));

		return rowView;
	}


}