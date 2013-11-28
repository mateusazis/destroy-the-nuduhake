package br.uff.pse.destroythenuduhake.drawing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import br.uff.pse.destroythenuduhake.game.control.AuthorRetriever;
import br.uff.pse.destroythenuduhake.dtn.DTNService;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.files.FileManager;

public class FreehandDrawingActivity extends Activity {
//	private static final String TAG = "FreehandDrawing";
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
        Button colorButton = (Button)findViewById(R.id.color_button);
        colorButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.colorPicker();
			}
		});
        
        Button widthButton = (Button)findViewById(R.id.width_button);
        widthButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
	    		alert.show();
	    		CircleView circle = (CircleView)alert.findViewById(R.id.circle_view);
	            seekBar = (SeekBar)alert.findViewById(R.id.seek_bar);
	            circle.setWid(drawView.getWid());
	            circle.setPaintFill(drawView.paint);
	            seekBar.setProgress((int)drawView.getWid());
				
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
        
        Button eraserButton = (Button)findViewById(R.id.eraser_button);
        eraserButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				drawView.eraser();				
			}
		});
        
        Button saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Bitmap save = Bitmap.createBitmap(drawView.image.getWidth(), drawView.image.getHeight(), Config.ARGB_8888);
				Canvas c = new Canvas(save);
				drawView.save();
				drawView.draw(c);
				
				Context ctx = FreehandDrawingActivity.this;
				
				GraphicAsset oldGA = drawView.getGraphicAsset();
				GraphicAsset newGA;
				if(oldGA.isOriginal()){
					String newAssetPath = FileManager.getAvaiableFilepath(ctx,getFilesDir().getAbsolutePath(),true);
					newGA = oldGA.makeCopy(AuthorRetriever.getAuthor(), newAssetPath);
				}
				else
					newGA = oldGA;
				
				newGA.editBitmap(save);
				FileManager.addAsset(newGA, ctx);					

				DTNService.assetToSendViaDtn = newGA;
				alertServiceToSend();

				finish();
			}
		});
        
        Button undoButton = (Button)findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.undo();
				
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
				drawView.setWid(progress, drawView.paint);
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

	public void alertServiceToSend()
	{
		try
		{

			Intent i = new Intent(this, DTNService.class);
			i.setAction(DTNService.SEND_BUNDLE_INTENT);
			startService(i);
			i = new Intent(this, DTNService.class);

		}
		catch (Exception e)
		{

		}
	
	}
}