package com.example.testlink.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by lxx on 2019/4/8.
 * Use by 完全自定义view
 */

public class SecondView extends View {

    private Paint paint;
    private Paint paintText;
    private RectF f;
    private RectF f2;
    private Path tPath;

    public SecondView(Context context) {
        super(context);
        initView(context);
    }


    public SecondView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SecondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setAntiAlias(true);
        f = new RectF(100,100,300,300);
        f2 = new RectF(105,95,305,295);

        //
        paintText =new Paint();
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setColor(Color.parseColor("#FF4B8d"));
        paintText.setAntiAlias(true);

        tPath = new Path();
        tPath.moveTo(100,350);
        tPath.lineTo(200,420);
        tPath.lineTo(250,380);
        tPath.lineTo(350,380);
        tPath.lineTo(350,380);

//        tPath.addArc(200, 200, 400, 400, -225, 225);
//        tPath.arcTo(400, 200, 600, 400, -180, 225, false);
//        tPath.lineTo(400, 542);


        RectF f3 = new RectF(350,280,450,480);
//        tPath.addArc(f3,0,35);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,getDefaultWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("哈哈哈哈哈哈哈", 10, 30, paint);

        canvas.drawArc(f,0,90,true,paint);
        paint.setColor(Color.parseColor("#76a9f1"));
        canvas.drawArc(f,92,192,true,paint);
        paint.setColor(Color.parseColor("#FFFF3B1C"));
        canvas.drawArc(f2,285,65,true,paint);
        paint.setColor(Color.parseColor("#FF4B8d"));
        canvas.drawArc(f,351,12,true,paint);

        canvas.drawPath(tPath,paintText);
        canvas.drawTextOnPath("ha你好啊哈哈哈哈哈哈哈",tPath,10,10,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 屏幕宽高
     *
     * @return
     */
    private int getDefaultWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Log.d("SecondView", "getDefaultWidth: " + outMetrics.widthPixels + "-------" + outMetrics.heightPixels);
        return Math.min(outMetrics.widthPixels, outMetrics.heightPixels);
    }
}
