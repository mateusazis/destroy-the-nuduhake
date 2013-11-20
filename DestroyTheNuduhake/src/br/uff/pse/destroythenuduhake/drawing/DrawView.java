package br.uff.pse.destroythenuduhake.drawing;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Cubemap;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

@SuppressLint("NewApi")
public class DrawView extends View implements OnTouchListener {
	private static final String TAG = "DrawView";
	
	Path path = new Path();
	List<Path> pathList= new ArrayList<Path>();
	List<Path> transformedPathList= new ArrayList<Path>();
	List<Paint> paintList= new ArrayList<Paint>();
	//HashMap<Path, Paint> pathList = new LinkedHashMap<Path, Paint>();
//	List<List<Point>> drawing = new ArrayList<List<Point>>();
//	List<Point> points = new ArrayList<Point>();
	private GraphicAsset graphicAsset;
	Bitmap image = null;
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Matrix initialOrientationTransformation = new Matrix();
	Matrix anotherOrientationTransformation = new Matrix();
	Matrix inverseInitialTransformation = new Matrix();
	Matrix inverseAnotherTransformation = new Matrix();
	Matrix nullMatrix = new Matrix();
	boolean firstTime = true;
	Bitmap showAsset;
	Bitmap savedAsset;
	Rect clipBounds;
	Canvas mCanvas;
	Rect clipBounds_canvas;
	Bitmap workingBitmap;
	int initialOrientation;
	boolean save = false;
	boolean erase = false;
	int centerX, centerY;
	private float wid = 20;
	float[] leftmostPoint;
	float[] rightmostPoint;
	float x = 0;
	float y = 0;

	//attributes relative to transformation
	float imageWidth;
	float imageHeight;
	float initialX;
	float initialY;
	float anotherX;
	float anotherY;
	float currentMeasureX;
	float currentMeasureY;
	float initialRatio;
	float anotherRatio;
	float zoomFactor;

	
	
	public float getWid() {
		return wid;
	}

	public void setWid(float wid, Paint p) {
		this.wid = wid;
		p.setStrokeWidth(wid/zoomFactor);		
	}
	
	public GraphicAsset getGraphicAsset(){
		return graphicAsset;
	}
	
	public void setGraphicAsset(GraphicAsset ga){
		this.graphicAsset = ga;
		if(graphicAsset != null)
			image = graphicAsset.getBitmap(getContext());
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void setAllScreen(int x, int y){
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		float ratioX = x/imageWidth;
		float ratioY = y/imageHeight;
		int delta;
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if(Build.VERSION.SDK_INT < 13)
			delta = display.getHeight() - y;
		else{
			Point size = new Point();
			display.getSize(size);
			delta = size.y - y;
		}
		
//		boolean rotate = false;
		initialRatio = Math.min(ratioX, ratioY);
//		if(ratio < 1)
//			ratio = (x*y)/(imageHeight*imageWidth);
//		if(imageHeight < imageWidth){
//			float aux = imageHeight;
//			imageHeight = imageWidth;
//			imageWidth = aux;
//		}
		
		
//		initialOrientationTransformation.postTranslate(imageWidth, 0);
//		initialOrientationTransformation.invert(inverseRotate);
		initialOrientationTransformation.setScale(initialRatio, initialRatio);
//		initialOrientationTransformation.postTranslate((x - imageWidth*initialRatio)/2, (y - imageHeight*initialRatio)/2);
		
		initialOrientationTransformation.invert(inverseInitialTransformation);
		
		float newX = y + delta;
		float newY = x - delta;
		
		ratioX = newX/imageWidth;
		ratioY = newY/imageHeight;
//		boolean rotate = false;
		anotherRatio = Math.min(ratioX, ratioY);
		
		anotherOrientationTransformation.setScale(anotherRatio, anotherRatio);
//		anotherOrientationTransformation.postTranslate((newX - imageWidth*anotherRatio)/2, (newY- imageHeight*anotherRatio)/2);
		
		anotherOrientationTransformation.invert(inverseAnotherTransformation);
		
		initialX = (x - imageWidth*initialRatio)/2;
		initialY = (y - imageHeight*initialRatio)/2;
		anotherX = (newX - imageHeight*anotherRatio)/2;
		anotherY = (newY - imageHeight*anotherRatio)/2;
		
		zoomFactor = initialRatio;
		setWid(wid, paint);
	}
	
	public void setCenter(int x, int y){
		initialOrientationTransformation.setTranslate((x - image.getWidth())/2, (y - image.getHeight())/2);
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
			initialOrientation = getResources().getConfiguration().orientation;
		}
	}
	
	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
//		if(newConfig.orientation != initialOrientation){
//			if(firstTime){
//				firstTime = false;
//				for(int i = 0; i < pathList.size(); i++){
//					pathList.get(i).transform(anotherOrientationTransformation, transformedPathList.get(i));
//					setWid(wid*anotherRatio/initialRatio, paintList.get(i));
//				}
//			} else {
//				for(int i = 0; i < transformedPathList.size(); i++){
//					transformedPathList.get(i).transform(anotherOrientationTransformation);
//					setWid(wid*anotherRatio/initialRatio, paintList.get(i));
//				}
//			}
//		} else {
//			for(int i = 0; i < transformedPathList.size(); i++){
//				transformedPathList.get(i).transform(initialOrientationTransformation);
//				setWid(wid*initialRatio/anotherRatio, paintList.get(i));
//			}
//		}
//		pathList.clear();
		
		//setDrawingCacheEnabled(true);
		//image = Bitmap.createBitmap(getDrawingCache(), clipBounds.left, clipBounds.top, clipBounds.right, clipBounds.bottom, inverseInitialTransformation, false);
		//setDrawingCacheEnabled(false);
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
//	    clipBounds_canvas = canvas.getClipBounds();
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
			if(image != null){
				if(getResources().getConfiguration().orientation == initialOrientation)
					canvas.concat(initialOrientationTransformation);
				 else 
					canvas.concat(anotherOrientationTransformation);	
				canvas.drawBitmap(image, nullMatrix, null);
				for(int i = 0; i < pathList.size(); i++)
					canvas.drawPath(pathList.get(i), paintList.get(i));
			}
		} else {
			canvas.drawColor(0, Mode.CLEAR);
			if(image != null){
				if(getResources().getConfiguration().orientation == initialOrientation)
					canvas.concat(inverseInitialTransformation);
				 else 
					canvas.concat(inverseAnotherTransformation);	
				canvas.drawBitmap(image, nullMatrix, null);
			}
				for(int i = 0; i < pathList.size(); i++)
					canvas.drawPath(pathList.get(i), paintList.get(i));
//			if(image != null)
//				if(getResources().getConfiguration().orientation == initialOrientation){
//					canvas.drawBitmap(image, initialOrientationTransformation, null);
//					for (int i = 0; i < pathList.size(); i++) {
//						pathList.get(i).transform(initialOrientationTransformation, transformedPathList.get(i));
//						canvas.drawPath(transformedPathList.get(i), paintList.get(i));
//					}
//				} else {
//					canvas.drawBitmap(image, anotherOrientationTransformation, null);
//					for (int i = 0; i < pathList.size(); i++) {
//						pathList.get(i).transform(anotherOrientationTransformation, transformedPathList.get(i));
//						canvas.drawPath(transformedPathList.get(i), paintList.get(i));
//					}
//				}
		}
	}
		
	public void save(){
		save = true;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
//	    x = event.getX();
//	    y = event.getY();
	    if(getResources().getConfiguration().orientation == initialOrientation){
	    	zoomFactor = initialRatio;
	    	currentMeasureX = initialX;
	    	currentMeasureY = initialY;
	    }
	    else{
	    	zoomFactor = anotherRatio;
	    	currentMeasureX = anotherX;
	    	currentMeasureY = anotherY;
	    }
		float x = event.getX() / zoomFactor;// - currentMeasureX/2; //- currentMeasureX)*2;// + clipBounds_canvas.left;
	    float y = event.getY() / zoomFactor;// - currentMeasureY/2;// - (imageHeight*zoomFactor);// - currentMeasureY)*2;// clipBounds_canvas.top;

	    
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
	    	transformedPathList.add(new Path());
	    	if(firstTime){
		    	pathList.add(new Path());
		    	path = pathList.get(pathList.size()-1);
	    	} else {
	    		path = transformedPathList.get(transformedPathList.size()-1);
	    	}
		    paintList.add(new Paint(paint));
		    path.setLastPoint(x, y);
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