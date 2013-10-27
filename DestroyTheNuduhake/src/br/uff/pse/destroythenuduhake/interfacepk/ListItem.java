package br.uff.pse.destroythenuduhake.interfacepk;

import br.uff.pse.destroythenuduhake.R;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter.RowType;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItem implements Item {
	//private final String		 filepath;
    //private final String         str1;
    //private final String         str2;
    //private final int             id;
    private  boolean        checked;
    private final Asset         asset;
    private final Bitmap b;
    private CheckBox cb;
    private ImageView iv;
    private boolean visible = true;
    public ListItem(Asset a,boolean c,Bitmap b) {
       // this.str1 = text1;
        //this.str2 = text2;
       	//this.checked = c;
       	//this.id = id;
       	//this.filepath = filepath;
    	this.asset = a;
    	this.checked = c;
    	this.b = b;
       	
       	
    }

    @Override
    public int getViewType() {
        return RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.my_list_item, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView text1 = (TextView) view.findViewById(R.id.list_content1);
        TextView text2 = (TextView) view.findViewById(R.id.list_content2);
        iv = (ImageView) view.findViewById(R.id.imageView1);
        cb = (CheckBox) view.findViewById(R.id.checkBox1);
        cb.setChecked(checked);
        iv.setImageBitmap(b);
        if(!visible)
        {
        	cb.setVisibility(View.INVISIBLE);
        }
        //cb.setChecked(FileManager.getCheckOptionFromFile(str2,ctx));
        text1.setText("Version: " + asset.getVersionNumber());
        text2.setText(asset.getAuthor().toString());

        return view;
    }
    public boolean getChecked()
    {
    	return checked;
    }
    public void setChecked(boolean b)
    {
    	checked = b;
    	cb.setChecked(checked);
    }
    public void setCheckBoxInv()
    {
    	visible = false;
    }
    public Asset getAsset()
    {
    	return asset;
    }

	public Bitmap getBitmap() {
		return b;
	}

}