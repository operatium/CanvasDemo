package com.canvas;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/2/13.
 */

public class QQCir extends View {
    private PointF p = new PointF(200,200) ;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x1234){
                QQCir.this.invalidate();
            }
        }
    };

    public QQCir(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                p = new PointF(event.getX(),event.getY());
                handler.sendEmptyMessage(0x1234);
                Log.e("show",p.toString());
            }
            return false;
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(Color.RED);
        canvas.drawCircle(p.x,p.y,30,paint);

        canvas.drawCircle(200,200,10,paint);
        canvas.drawLine(200,200,p.x,p.y,paint);
    }

}
