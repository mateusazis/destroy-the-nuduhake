package br.uff.pse.drawing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class FreehandDrawing extends Activity {
	private static final String TAG = "FingerPaint";
	DrawView drawView;
	SeekBar seekBar;
    AlertDialog.Builder builder;
    AlertDialog alert;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // lock screen orientation (stops screen clearing when rotating phone)
        setRequestedOrientation(getResources().getConfiguration().orientation);
        
        setContentView(R.layout.freehand_drawing_main);
        drawView = (DrawView)findViewById(R.id.draw_view);
        drawView.setBackgroundColor(Color.BLACK);
        drawView.requestFocus();
//        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
//        View layout = inflater.inflate(R.layout.color_picker_dialog, (ViewGroup)findViewById(R.id.RelativeLayout1));
        builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int progress = seekBar.getProgress();
				drawView.setWid(progress);
				drawView.changeWidth(progress);
			}
		});
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setTitle(R.string.dialog_title);
        builder.setView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.color_picker_dialog, null));        
        alert = builder.create();
//        

        //seekBar.setVisibility(View.INVISIBLE);
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
    	int itemID = item.getItemId();
    	if(itemID == R.id.clear_id)
    		drawView.clearScreen();
    	if(itemID == R.id.undo_id)
    		drawView.undo();
    	if(itemID == R.id.change_color_id)
    		drawView.colorPicker();
    	if(itemID == R.id.change_bg_color_id)
    		drawView.bgColorPicker();
    	if(itemID == R.id.change_width_id){    		
    		alert.show();
    		CircleView circle = (CircleView)alert.findViewById(R.id.circle_view);
            seekBar = (SeekBar)alert.findViewById(R.id.seek_bar);
            circle.setWid(drawView.getWid());
            seekBar.setProgress(drawView.getWid());
			
	        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	            CircleView circle = (CircleView)alert.findViewById(R.id.circle_view);
			            public void onStopTrackingTouch(SeekBar arg0) {
			                // TODO Auto-generated method stub
			            }
			
			            public void onStartTrackingTouch(SeekBar arg0) {
			                // TODO Auto-generated method stub
			            }
			
			            public void onProgressChanged(SeekBar sb, int progress, boolean arg2) {
			                // TODO Auto-generated method stub
			            	circle.setWid(progress);
			            	//Bitmap b = Bitmap.createBitmap(circle.getWidth(), circle.getHeight(), Bitmap.Config.ARGB_8888);
			            	Canvas c = new Canvas();
			            	circle.draw(c);
			            }
			        });

    		//LayoutInflater inflater = getLayoutInflater();
    		//builder.setView(inflater.inflate(R.layout.color_picker_dialog,null));
    		
//    		alert = builder.create();
          
//
//            seekBar.setMax(100);
//            seekBar.setProgress(5);
//    		if(!seekBar.isShown())
//    			seekBar.setVisibility(View.VISIBLE);
//    		else
//    			seekBar.setVisibility(View.INVISIBLE);
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
//    	default : {
    		return true;
//    	}
//    	}
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