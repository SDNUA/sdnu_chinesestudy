package com.sdnu.study.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 画板视图
 * @author Administrator
 *
 */
public class WrittingView2  extends View{
	
	private Paint paint;
	public WrittingView2(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint=new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.RED);
	}

	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawRect(100,100,200,200, paint);
	}
	
}
