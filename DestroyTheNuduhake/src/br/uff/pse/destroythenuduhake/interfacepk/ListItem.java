package br.uff.pse.destroythenuduhake.interfacepk;

import br.uff.pse.destroythenuduhake.R;
import br.uff.pse.destroythenuduhake.R.id;
import br.uff.pse.destroythenuduhake.R.layout;
import br.uff.pse.destroythenuduhake.interfacepk.TwoTextArrayAdapter.RowType;
import br.uff.pse.files.FileManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListItem implements Item {
    private final String         str1;
    private final String         str2;
    private  boolean        checked;;
    public ListItem( String text1, String text2,boolean c) {
        this.str1 = text1;
        this.str2 = text2;
       	this.checked = c;
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
        CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1);
        cb.setChecked(checked);
        //cb.setChecked(FileManager.getCheckOptionFromFile(str2,ctx));
        text1.setText(str1);
        text2.setText(str2);

        return view;
    }
    public boolean getChecked()
    {
    	return checked;
    }
    public void setChecked(boolean b)
    {
    	checked = b;
    }
}