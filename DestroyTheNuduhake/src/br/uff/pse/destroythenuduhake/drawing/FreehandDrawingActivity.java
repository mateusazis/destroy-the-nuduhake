package br.uff.pse.destroythenuduhake.drawing;

import br.uff.pse.destroythenuduhake.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class FreehandDrawingActivity extends Activity {
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
        builder.setView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.width_picker_dialog, null));        
        alert = builder.create();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.layout.paint_menu, menu);
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
            circle.setPaintFill(drawView.paint);
            seekBar.setProgress(drawView.getWid());
			
	        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	            CircleView circle = (CircleView)alert.findViewById(R.id.circle_view);
			            @Override
						public void onStopTrackingTouch(SeekBar arg0) {
			                // TODO Auto-generated method stub
			            }
			
			            @Override
						public void onStartTrackingTouch(SeekBar arg0) {
			                // TODO Auto-generated method stub
			            }
			
			            @Override
						public void onProgressChanged(SeekBar sb, int progress, boolean arg2) {
			                // TODO Auto-generated method stub
			            	circle.setWid(progress);
			            	Canvas c = new Canvas();
			            	circle.draw(c);
			            }
			        });
    	}
    	return true;
    }

//    
//    void setCustomBackground(DrawView v) {
//    	Intent fileChooserIntent = new Intent();
//    	fileChooserIntent.addCategory(Intent.CATEGORY_OPENABLE);
//    	fileChooserIntent.setType("image/*");
//    	fileChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
//    	startActivityForResult(Intent.createChooser(fileChooserIntent, "Select Picture"), 1);
//    	/*
//    	// menu option for setting a custom background
//    	String Url = "http://www.google.ca";	// http://www.google.ca
//    	Intent fileChooserIntent = new Intent(Intent.ACTION_CHOOSER, Uri.parse(Url));
//    	this.startActivity(fileChooserIntent);
//    	*/
//    }
//    
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    	// if statement prevents force close error when picture isn't selected
//    	if (resultCode == RESULT_OK)
//    	{
//	    	Uri resultUri = data.getData();
//	    	//String resultString = data.getData().toString();
//	    	
//	    	String drawString = resultUri.getPath();
//	    	String galleryString = getGalleryPath(resultUri);
//	    	
//	    	// if Gallery app was used
//	    	if (galleryString != null)
//	    	{
//	    		Log.d(TAG, galleryString);
//	    		drawString = galleryString;
//	    	}
//	    	// else another file manager was used
//	    	else
//	    	{
//	    		Log.d(TAG, drawString);
//		    	//File Manager: "content://org.openintents.cmfilemanager/mimetype//mnt/sdcard/DCIM/Camera/IMG_20110909_210412.jpg"
//		    	//ASTRO:        "file:///mnt/sdcard/DCIM/Camera/IMG_20110924_133324.jpg"
//		    	if (drawString.contains("//"))
//		    	{
//		    		drawString = drawString.substring(drawString.lastIndexOf("//"));
//		    	}
//	    	}
//	    	
//	    	// set the background to the selected picture
//	    	if (drawString.length() > 0)
//	    	{
//	    		Drawable drawBackground = Drawable.createFromPath(drawString);
//	    		drawView.setBackgroundDrawable(drawBackground);
//	    	}
//	    	
//    	}
//    }
    
//    // used when trying to get an image path from the URI returned by the Gallery app
//    public String getGalleryPath(Uri uri) {
//    	String[] projection = { MediaColumns.DATA };
//    	Cursor cursor = managedQuery(uri, projection, null, null, null);
//    	
//    	if (cursor != null)
//    	{
//    		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//    		cursor.moveToFirst();
//    		return cursor.getString(column_index);
//    	}
//    	
//    	
//    	return null;
//    }

}