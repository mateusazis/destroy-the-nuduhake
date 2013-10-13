package br.uff.pse.destroythenuduhake.drawing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
	private static final String TAG = "DrawView";
	
	Path path = new Path();
	List<Path> pathList= new ArrayList<Path>();
	List<Paint> paintList= new ArrayList<Paint>();
	//HashMap<Path, Paint> pathList = new LinkedHashMap<Path, Paint>();
//	List<List<Point>> drawing = new ArrayList<List<Point>>();
//	List<Point> points = new ArrayList<Point>();
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Random gen;
	int bgColor = Color.BLACK;
	private int wid = 10;
	
	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	float x = 0;
	float y = 0;
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// set default colour to white
//		col_mode = 0;
		paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.WHITE);
	    paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(getWid());
	    setFocusable(true);
		setFocusableInTouchMode(true);
		
		this.setOnTouchListener(this);
		}
	
	// used to clear the screen
	public void clearScreen () {
		pathList.clear();
		paintList.clear();
		forceRedraw();
	}
	
	public void undo() {
		if(pathList.size() > 0){
			pathList.remove(pathList.size()-1);
			paintList.remove(paintList.size()-1);
			forceRedraw();
		}
	}
	
	
	/**
	 * Force view to redraw. Without this points aren't cleared until next action
	 */
	public void forceRedraw() {
		invalidate();
	}
	
	// used to set drawing colour
	
	public void colorPicker() {
		AmbilWarnaDialog dialog = new AmbilWarnaDialog(getContext(), paint.getColor(), new OnAmbilWarnaListener() {
			
			@Override
			public void onOk(AmbilWarnaDialog dialog, int color) {
				paint.setColor(color);
			} 	
			
			@Override
			public void onCancel(AmbilWarnaDialog arg0) {
				// TODO Auto-generated method stub
			}
		});
		dialog.show();
	}
	
	public void bgColorPicker() {
		AmbilWarnaDialog dialog = new AmbilWarnaDialog(getContext(), bgColor, new OnAmbilWarnaListener() {
			
			@Override
			public void onOk(AmbilWarnaDialog dialog, int color) {
				bgColor = color;
				setBackgroundColor(bgColor);
			} 	
			
			@Override
			public void onCancel(AmbilWarnaDialog arg0) {
				// TODO Auto-generated method stub
			}
		});	
		dialog.show();
	}

	// used to set drawing width
	public void changeWidth(int progress) {
		paint.setStrokeWidth(progress);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		for (int i = 0; i < pathList.size(); i++) {
			canvas.drawPath(pathList.get(i), paintList.get(i));
		}
	}
		
	@Override
	public boolean onTouch(View view, MotionEvent event) {
	    x = event.getX();
	    y = event.getY();
	    
	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	        pathList.add(new Path());
	        paintList.add(new Paint(paint));
	        path = pathList.get(pathList.size()-1);
	        path.setLastPoint(--x, y);
	        path.lineTo(x, y);
	        break;
	    case MotionEvent.ACTION_MOVE:
	        path.lineTo(x, y);
	        path.moveTo(x, y);
	        break;
	    case MotionEvent.ACTION_UP:
	    	path.lineTo(x, y);
	        break;
	    default:
	        return false;
	    }
	    forceRedraw();
	    return true;
	}
}