package br.uff.pse.drawing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class DrawScreen extends ViewGroup{

	DrawView drawView;
	SeekBar seekBar;
	
	public DrawScreen(Context context) {
		super(context);
		
		
		drawView.setBackgroundColor(Color.BLACK);
        drawView.requestFocus();
        
        seekBar = new SeekBar(getContext());
        seekBar.setMax(15);
//      seekBar.setIndeterminate(true);

        ShapeDrawable thumb = new ShapeDrawable(new OvalShape());

        thumb.setIntrinsicHeight(80);
        thumb.setIntrinsicWidth(30);
        seekBar.setThumb(thumb);
        seekBar.setProgress(1);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setBackgroundColor(Color.BLUE);

        LayoutParams lp = new LayoutParams(200, 50);
        seekBar.setLayoutParams(lp);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
			public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                System.out.println(".....111.......");
            }

            @Override
			public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                System.out.println(".....222.......");
            }

            @Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                System.out.println(".....333......."+arg1);
            }
        });
        //setContentView(seekBar);

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}	

}
