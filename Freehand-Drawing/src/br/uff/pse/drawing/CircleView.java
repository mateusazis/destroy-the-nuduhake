package br.uff.pse.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

	private Paint paintFill = new Paint();
	private Paint paintStroke;
	private float wid = 10;
	
	public float getWid() {
		return wid;
	}

	public void setWid(float wid) {
		this.wid = wid;
	}

	public Paint getPaintFill() {
		return paintFill;
	}

	public void setPaintFill(Paint paint) {
		paintFill.set(paint);
		paintFill.setStyle(Paint.Style.FILL);
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintStroke.setStyle(Paint.Style.STROKE);
		paintStroke.setARGB(127, 127, 127, 127);
		paintStroke.setStrokeWidth(1);
		paintStroke.setStrokeCap(Paint.Cap.ROUND);
	}

	public CircleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(getWid() != 0){
			canvas.drawCircle(getWidth()/2, getHeight()/2, getWid()/2, paintStroke);
			canvas.drawCircle(getWidth()/2, getHeight()/2, getWid()/2, paintFill);
		} else {
			canvas.drawCircle(getWidth()/2, getHeight()/2, 0.5f, paintStroke);
			canvas.drawCircle(getWidth()/2, getHeight()/2, 0.5f, paintFill);
		}
		invalidate();
	}

	
}
