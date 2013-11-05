package br.uff.pse.destroythenuduhake.drawing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import br.uff.pse.destroythenuduhake.AssetsWorkshopActivity;
import br.uff.pse.destroythenuduhake.R;
import br.uff.pse.destroythenuduhake.dtn.AuthorRetriever;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.files.FileManager;

public class FreehandDrawingActivity extends Activity {
	private static final String TAG = "FreehandDrawing";
	DrawView drawView;
	SeekBar seekBar;
    AlertDialog.Builder builder;
    AlertDialog alert;
    
    /** Called when the activity is first created. */
    @SuppressLint("NewApi")
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
        drawView.setGraphicAsset(AssetsWorkshopActivity.asset);
		if(drawView.image != null){
	        OnGlobalLayoutListener list = new OnGlobalLayoutListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void onGlobalLayout() {
					int viewWidth = drawView.getWidth();
					int viewHeight = drawView.getHeight();
					drawView.setAllScreen(viewWidth, viewHeight);
					if(Build.VERSION.SDK_INT < 16){
					drawView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					} else {
					drawView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					}
				}
			};
			drawView.getViewTreeObserver().addOnGlobalLayoutListener(list);
		}
        drawView.requestFocus();
        Button widthButton = (Button)findViewById(R.id.width_button);
        widthButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
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
		});
        Button colorButton = (Button)findViewById(R.id.color_button);
        colorButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.colorPicker();
			}
		});
        Button saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Bitmap save = Bitmap.createBitmap(drawView.image.getWidth(), drawView.image.getHeight(), Config.ARGB_8888);
				Bitmap work = Bitmap.createBitmap(drawView.getWidth(), drawView.getHeight(), Config.ARGB_8888);
				Canvas c = new Canvas(work);
				drawView.draw(c);
				drawView.save();
				drawView.draw(c);
				drawView.showAsset = Bitmap.createBitmap(work);
				Canvas saveCanvas = new Canvas(save);
				saveCanvas.drawBitmap(drawView.showAsset, drawView.inverseTransformation, null);
				
				Context ctx = FreehandDrawingActivity.this;
				
				GraphicAsset oldGA = drawView.getGraphicAsset();
				GraphicAsset newGA;
				if(oldGA.isOriginal()){
					String newAssetPath = FileManager.getAvaiableFilepath(ctx,getFilesDir().getAbsolutePath(),true);
					newGA = oldGA.makeCopy(AuthorRetriever.getAuthor(), newAssetPath);
					FileManager.addAsset(newGA, ctx);
				}
				else
					newGA = oldGA;
				
				save = Bitmap.createBitmap(save);
				//newGA.setBitmap(save);
				newGA.editBitmap(save);
				FileManager.saveListFile(FreehandDrawingActivity.this);
				
				finish();
			}
		});
        Button undoButton = (Button)findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.erase = true;
				//drawView.undo();
				
			}
		});
        Button clearButton = (Button)findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.clearScreen();
			}
		});
        builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int progress = seekBar.getProgress();
				drawView.setWid(progress);
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