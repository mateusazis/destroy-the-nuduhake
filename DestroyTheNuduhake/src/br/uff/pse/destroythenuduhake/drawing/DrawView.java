package br.uff.pse.destroythenuduhake.drawing;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class DrawView extends View implements OnTouchListener {
	private static final String TAG = "DrawView";
	
	Path path = new Path();
	List<Path> pathList= new ArrayList<Path>();
	List<Paint> paintList= new ArrayList<Paint>();
	//HashMap<Path, Paint> pathList = new LinkedHashMap<Path, Paint>();
//	List<List<Point>> drawing = new ArrayList<List<Point>>();
//	List<Point> points = new ArrayList<Point>();
	private GraphicAsset graphicAsset;
	Bitmap image = null;
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Matrix transformation = new Matrix();
	Matrix inverseRotate = new Matrix();
	Matrix inverseTransformation = new Matrix();
	Bitmap showAsset;
	Bitmap savedAsset;
	Canvas mCanvas;
	Bitmap workingBitmap;
	float ratio;
	boolean save = false;
	boolean erase = false;
	int centerX, centerY;
	private int wid = 20;
	float[] leftmostPoint;
	float[] rightmostPoint;
	float x = 0;
	float y = 0;
//	ImageView iv;
	
	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
		paint.setStrokeWidth(wid);		
	}
	
	public GraphicAsset getGraphicAsset(){
		return graphicAsset;
	}
	
	public void setGraphicAsset(GraphicAsset ga){
		this.graphicAsset = ga;
		if(graphicAsset != null)
			image = graphicAsset.getBitmap(getContext());
	}
	
	public void setAllScreen(int x, int y){
		float imageWidth = image.getWidth();
		float imageHeight = image.getHeight();
		float ratioX = x/imageWidth;
		float ratioY = y/imageHeight;
		boolean rotate = false;
		ratio = Math.min(ratioX, ratioY);
		if(ratio < 1)
			ratio = (x*y)/(imageHeight*imageWidth);
		if(imageHeight < imageWidth){
			rotate = true;
			float aux = imageHeight;
			imageHeight = imageWidth;
			imageWidth = aux;
		}
		
		if(rotate){
			transformation.setRotate(90);
			transformation.postTranslate(imageWidth, 0);
			transformation.invert(inverseRotate);
		}
		transformation.postScale(ratio, ratio);
		transformation.postTranslate((getWidth() - imageWidth*ratio)/2, (getHeight() - imageHeight*ratio)/2);
		
		transformation.invert(inverseTransformation);
		if(rotate)
			inverseRotate.postConcat(transformation);
	}
	
	public void setCenter(int x, int y){
		transformation.setTranslate((x - image.getWidth())/2, (y - image.getHeight())/2);
		leftmostPoint = new float[2];
		leftmostPoint[0] = (x - image.getWidth())/2;
		leftmostPoint[1] = (y - image.getHeight())/2;
		rightmostPoint = new float[2];
		rightmostPoint[0] = (x + image.getWidth())/2;
		rightmostPoint[1] = (y + image.getWidth())/2;
	}
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// set default colour to white
//		col_mode = 0;
		paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.WHITE);
	    paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(getWid());
		setBackgroundColor(Color.TRANSPARENT);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
	}
	

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if(oldw == 0 && oldh == 0){
			workingBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
			mCanvas = new Canvas(workingBitmap);
		}
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
				erase = false;
			} 	
			
			@Override
			public void onCancel(AmbilWarnaDialog arg0) {
				// TODO Auto-generated method stub
			}
		});
		dialog.show();
	}
	
//	public void bgColorPicker() {
//		AmbilWarnaDialog dialog = new AmbilWarnaDialog(getContext(), bgColor, new OnAmbilWarnaListener() {
//			
//			@Override
//			public void onOk(AmbilWarnaDialog dialog, int color) {
//				bgColor = color;
//				setBackgroundColor(bgColor);
//			} 	
//			
//			@Override
//			public void onCancel(AmbilWarnaDialog arg0) {
//				// TODO Auto-generated method stub
//			}
//		});	
//		dialog.show();
//	}
	
	@Override
	public void onDraw(Canvas canvas) {
//		
//		if(erase){
//			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//			if(image != null)
//				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
//					mCanvas.drawBitmap(image, inverseRotate, null);
//				else
//					mCanvas.drawBitmap(image, transformation, null);
//			for (int i = 0; i < pathList.size(); i++) {
//				mCanvas.drawPath(pathList.get(i), paintList.get(i));
//			}
//			canvas.drawBitmap(workingBitmap, new Matrix(), null);
//		}
		if(!save){
			canvas.drawColor(Color.WHITE);
			if(image != null)
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
					canvas.drawBitmap(image, inverseRotate, null);
				else
					canvas.drawBitmap(image, transformation, null);
			for (int i = 0; i < pathList.size(); i++) {
				canvas.drawPath(pathList.get(i), paintList.get(i));
			}
		} else {
			canvas.drawColor(0, Mode.CLEAR);
			if(image != null)
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
					canvas.drawBitmap(image, inverseRotate, null);
				else
					canvas.drawBitmap(image, transformation, null);
			for (int i = 0; i < pathList.size(); i++) {
				canvas.drawPath(pathList.get(i), paintList.get(i));
			}
		}
	}
		
	public void save(){
		save = true;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
	    x = event.getX();
	    y = event.getY();
	    
//	    if((x-wid)/2 < leftmostPoint[0])
//	    	leftmostPoint[0] = (x-wid)/2;
//	    else if((x-wid)/2 > rightmostPoint[0])
//	    	rightmostPoint[0] = (x+wid)/2;
//	    
//	    if((y+wid)/2 < leftmostPoint[1])
//	    	leftmostPoint[1] = (y-wid)/2;
//	    else if(y > rightmostPoint[1])
//	    	rightmostPoint[1] = (y+wid)/2;
	    
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