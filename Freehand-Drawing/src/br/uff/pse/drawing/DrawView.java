package br.uff.pse.drawing;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.text.method.MovementMethod;
import android.util.Log;
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
	Paint paint = new Paint();
	Random gen;
	int col_mode;
	int wid_mode;
	float x = 0;
	float y = 0;
	
	
	public DrawView(Context context) {
		super(context);
		
		// set default colour to white
//		col_mode = 0;
		paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.WHITE);
	    paint.setStrokeCap(Paint.Cap.ROUND);
	    paint.setStrokeWidth(5);

		// set default width to 7px
		wid_mode = 10;
		
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		this.setOnTouchListener(this);
		
		paint.setAntiAlias(true);
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
	public void changeColour (int col_in) {
		col_mode = col_in;
		paint.setColor(col_mode);
	}

	// used to set drawing width
	public void changeWidth (int wid_in) {
		wid_mode = wid_in;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		for (int i = 0; i < pathList.size(); i++) {
			canvas.drawPath(pathList.get(i), paintList.get(i));
		}
	}
		
	public boolean onTouch(View view, MotionEvent event) {
		
		int new_col = 0;
		if (col_mode < 0) {
			gen = new Random();
			col_mode = gen.nextInt( 8 );
		}
		// This if statement may be redundant now
		if (col_mode >= 0) {
			switch (col_mode) {
				case 0 : {
					new_col = Color.WHITE;
					break;
				}
				case 1 : {
					new_col = Color.BLUE;
					break;
				}
				case 2 : {
					new_col = Color.CYAN;
					break;
				}
				case 3 : {
					new_col = Color.GREEN;
					break;
				}
				case 4 : {
					new_col = Color.MAGENTA;
					break;
				}
				case 5 : {
					new_col = Color.RED;
					break;
				}
				case 6 : {
					new_col = Color.YELLOW;
					break;
				}
				case 7 : {
					new_col = Color.BLACK;
					break;
				}
			}
			paint.setColor(new_col);
		}
		
		paint.setStrokeWidth(wid_mode);
		
	    x = event.getX();
	    y = event.getY();
	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	        pathList.add(new Path());
	        paintList.add(new Paint(paint));
	        path = pathList.get(pathList.size()-1);
	        path.moveTo(x, y);
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