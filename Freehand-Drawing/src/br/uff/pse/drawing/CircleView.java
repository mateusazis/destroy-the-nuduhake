package br.uff.pse.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

public class CircleView extends View {

	Paint paint;
	private float wid = 10;
	
	public float getWid() {
		return wid;
	}

	public void setWid(float wid) {
		this.wid = wid;
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setStrokeCap(Paint.Cap.ROUND);
	}

	public CircleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(getWid() != 0)
			canvas.drawCircle(getWidth()/2, getHeight()/2, getWid()/2, paint);
		else
			canvas.drawCircle(getWidth()/2, getHeight()/2, 0.5f, paint);
		invalidate();
	}

	
}
