package br.uff.pse.destroythenuduhake;


import android.content.Context;
//import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
		try
		{
			if(values[position].equals("Categoria"))
			{
				View rowView = convertView;
	
				if(rowView == null)
				{
					LayoutInflater inflater = LayoutInflater.from(context);
		        	rowView = inflater.inflate(R.layout.headerlayout, parent, false);
				}
		        
				
	
				TextView textView = (TextView) rowView.findViewById(R.id.textView1);
				textView.setText("Categoria");
				
				return rowView;
	
			}
			else
			{
				View rowView = convertView;
	
				if(rowView == null)
				{
					LayoutInflater inflater = LayoutInflater.from(context);
		        	rowView = inflater.inflate(layoutID, parent, false);
				}
		        
				
	
				TextView textView = (TextView) rowView.findViewById(R.id.label);
//				ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
				textView.setText(values[position]);
				
				return rowView;
			}
		}	
		catch(Exception e)
		{
			
		}
		return null;
	}


}