package br.uff.pse.destroythenuduhake.drawing;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import br.uff.pse.destroythenuduhake.AssetsWorkshopActivity;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
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
	Matrix inverseTransformation = new Matrix();
	Bitmap savedAsset;
	boolean save = false;
	int centerX, centerY;
	int bgColor = Color.TRANSPARENT;
	private int wid = 20;
	float[] leftmostPoint;
	float[] rightmostPoint;
	float x = 0;
	float y = 0;
	ImageView iv;
	
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
		float ratio = 0;
		if(imageHeight >= imageWidth){
			if(ratioY <= ratioX)
				ratio = ratioY;
			else
				ratio = ratioX;
		} else {
			rotate = true;
			float aux = imageHeight;
			imageHeight = imageWidth;
			imageWidth = aux;
			if(ratioX <= ratioY)
				ratio = ratioX;
			else
				ratio = ratioY;
		}
		
		if(rotate){
			transformation.setRotate(90);
			transformation.postTranslate(imageWidth, 0);
		}
		transformation.postScale(ratio, ratio);
		transformation.postTranslate((getWidth() - imageWidth*ratio)/2, (getHeight() - imageHeight*ratio)/2);
		
		transformation.invert(inverseTransformation);
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
	
	@Override
	public void onDraw(Canvas canvas) {
		if(!save){
			if(image != null)
				canvas.drawBitmap(image, transformation, null);
			for (int i = 0; i < pathList.size(); i++) {
				canvas.drawPath(pathList.get(i), paintList.get(i));
			}
		} else {
			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(savedAsset, inverseTransformation, null);
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