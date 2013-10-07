package br.uff.pse.drawing;

import br.uff.pse.drawing.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class FreehandDrawing extends Activity {
	private static final String TAG = "FingerPaint";
	DrawView drawView;
	SeekBar seekBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // lock screen orientation (stops screen clearing when rotating phone)
        setRequestedOrientation(getResources().getConfiguration().orientation);
        
        setContentView(R.layout.main);
        drawView = (DrawView)findViewById(R.id.draw_view);
        drawView.setBackgroundColor(Color.BLACK);
        drawView.requestFocus();
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

        	   @Override 
        	   public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        		   drawView.changeWidth(progress);
        	   } 

        	   @Override 
        	   public void onStartTrackingTouch(SeekBar seekBar) { 
        	   } 

        	   @Override 
        	   public void onStopTrackingTouch(SeekBar seekBar) { 
        	    // TODO Auto-generated method stub 
        	   } 
        	       });

        seekBar.setMax(100);
        seekBar.setProgress(1);
        seekBar.setVisibility(View.INVISIBLE);
//        seekBar.setBackgroundColor(Color.BLUE);
//
//        LayoutParams lp = new LayoutParams(200, 50);
//        seekBar.setLayoutParams(lp);
//        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//
//            public void onStopTrackingTouch(SeekBar arg0) {
//                // TODO Auto-generated method stub
//                System.out.println(".....111.......");
//            }
//
//            public void onStartTrackingTouch(SeekBar arg0) {
//                // TODO Auto-generated method stub
//                System.out.println(".....222.......");
//            }
//
//            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
//                // TODO Auto-generated method stub
//                System.out.println(".....333......."+arg1);
//            }
//        });
//        //setContentView(seekBar);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.paint_menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle item selection
    	switch (item.getItemId()) {
    	case R.id.clear_id : {
    		drawView.clearScreen();
    		return true;
    	}
    	case R.id.undo_id : {
    		drawView.undo();
    		return true;
    	}
    	case R.id.change_color_id: {
    		drawView.colorPicker();
    		return true;
    	}
    	case R.id.change_bg_color_id: {
    		drawView.bgColorPicker();
    		return true;
    	}
    	case R.id.change_width_id: {
    		if(!seekBar.isShown())
    			seekBar.setVisibility(View.VISIBLE);
    		else
    			seekBar.setVisibility(View.INVISIBLE);
    		return true;
    	}
/**    	case R.id.p_white_id : {
    		drawView.changeColour(0);
    		return true;
    	}
    	case R.id.p_blue_id : {
    		drawView.changeColour(1);
    		return true;
    	}
    	case R.id.p_lblue_id : {
    		drawView.changeColour(2);
    		return true;
    	}
    	case R.id.p_green_id : {
    		drawView.changeColour(3);
    		return true;
    	}
    	case R.id.p_pink_id : {
    		drawView.changeColour(4);
    		return true;
    	}
    	case R.id.p_red_id : {
    		drawView.changeColour(5);
    		return true;
    	}
    	case R.id.p_yellow_id : {
    		drawView.changeColour(6);
    		return true;
    	}
    	case R.id.p_black_id : {
    		drawView.changeColour(7);
    		return true;
    	}
    	case R.id.p_random_id : {
    		drawView.changeColour(-1);
    		return true;
    	}
    	case R.id.b_white_id : {
    		drawView.setBackgroundColor(Color.WHITE);
    		return true;
    	}
    	case R.id.b_blue_id : {
    		drawView.setBackgroundColor(Color.BLUE);
    		return true;
    	}
    	case R.id.b_lblue_id : {
    		drawView.setBackgroundColor(Color.CYAN);
    		return true;
    	}
    	case R.id.b_green_id : {
    		drawView.setBackgroundColor(Color.GREEN);
    		return true;
    	}
    	case R.id.b_pink_id : {
    		drawView.setBackgroundColor(Color.MAGENTA);
    		return true;
    	}
    	case R.id.b_red_id : {
    		drawView.setBackgroundColor(Color.RED);
    		return true;
    	}
    	case R.id.b_yellow_id : {
    		drawView.setBackgroundColor(Color.YELLOW);
    		return true;
    	}
    	case R.id.b_black_id : {
    		drawView.setBackgroundColor(Color.BLACK);
    		return true;
    	}
    	case R.id.b_custom_id : {
    		setCustomBackground(drawView);
    		return true;
    	}
    	case R.id.w_xsmall : {
    		drawView.changeWidth(0);
    		return true;
    	}
    	case R.id.w_small : {
    		drawView.changeWidth(5);
    		return true;
    	}
    	case R.id.w_medium : {
    		drawView.changeWidth(10);
    		return true;
    	}
    	case R.id.w_large : {
    		drawView.changeWidth(15);
    		return true;
    	}
    	case R.id.w_xlarge : {
    		drawView.changeWidth(20);
    		return true;
    	}*/
    	default : {
    		return true;
    	}
    	}
    }
    
    void setCustomBackground(DrawView v) {
    	Intent fileChooserIntent = new Intent();
    	fileChooserIntent.addCategory(Intent.CATEGORY_OPENABLE);
    	fileChooserIntent.setType("image/*");
    	fileChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
    	startActivityForResult(Intent.createChooser(fileChooserIntent, "Select Picture"), 1);
    	/*
    	// menu option for setting a custom background
    	String Url = "http://www.google.ca";	// http://www.google.ca
    	Intent fileChooserIntent = new Intent(Intent.ACTION_CHOOSER, Uri.parse(Url));
    	this.startActivity(fileChooserIntent);
    	*/
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// if statement prevents force close error when picture isn't selected
    	if (resultCode == RESULT_OK)
    	{
	    	Uri resultUri = data.getData();
	    	//String resultString = data.getData().toString();
	    	
	    	String drawString = resultUri.getPath();
	    	String galleryString = getGalleryPath(resultUri);
	    	
	    	// if Gallery app was used
	    	if (galleryString != null)
	    	{
	    		Log.d(TAG, galleryString);
	    		drawString = galleryString;
	    	}
	    	// else another file manager was used
	    	else
	    	{
	    		Log.d(TAG, drawString);
		    	//File Manager: "content://org.openintents.cmfilemanager/mimetype//mnt/sdcard/DCIM/Camera/IMG_20110909_210412.jpg"
		    	//ASTRO:        "file:///mnt/sdcard/DCIM/Camera/IMG_20110924_133324.jpg"
		    	if (drawString.contains("//"))
		    	{
		    		drawString = drawString.substring(drawString.lastIndexOf("//"));
		    	}
	    	}
	    	
	    	// set the background to the selected picture
	    	if (drawString.length() > 0)
	    	{
	    		Drawable drawBackground = Drawable.createFromPath(drawString);
	    		drawView.setBackgroundDrawable(drawBackground);
	    	}
	    	
    	}
    }
    
    // used when trying to get an image path from the URI returned by the Gallery app
    public String getGalleryPath(Uri uri) {
    	String[] projection = { MediaColumns.DATA };
    	Cursor cursor = managedQuery(uri, projection, null, null, null);
    	
    	if (cursor != null)
    	{
    		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
    		cursor.moveToFirst();
    		return cursor.getString(column_index);
    	}
    	
    	
    	return null;
    }

}