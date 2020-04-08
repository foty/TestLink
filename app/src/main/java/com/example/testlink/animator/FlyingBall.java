package com.example.testlink.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


/**
 * Create by lxx
 * Date : 2020/4/7 17:36
 * Use by 一个弹弹球。案例参考 https://www.gcssloop.com/customview/gestruedector
 */
public class FlyingBall extends View {

    private GestureDetector gestureDetector;

    /**
     * 开始X坐标
     */
    private float startX;
    /**
     * 开始Y坐标
     */
    private float startY;

    private float speedX = 0;
    private float speedY = 0;
    /**
     * 水平方向反向(速度)
     */
    private boolean reverseX;
    /**
     * 垂直方向反向(速度)
     */
    private boolean reverseY;

    private int radius = 80;

    private int height;
    private int width;
    private Paint paint;


    public FlyingBall(Context context) {
        this(context, null, 0);
    }

    public FlyingBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyingBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        gestureDetector = new GestureDetector(context, listener);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);

    }

    private GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("lxx", "velocityX= " + velocityX + "  velocityY= " + velocityY);
            speedX = velocityX;
            speedY = velocityY;

            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 0);

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(startX, startY, radius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        startX = width / 2;
        startY = height / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("lxx", "DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                startX = event.getX();
                startY = event.getY();

                fixLocation();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("lxx", "UP");
                break;
        }

        return true;
    }

    /**
     * 修正位置坐标
     */
    private boolean fixLocation() {
        boolean reverse = false;
        if (startX - radius <= 0) {
            startX = radius;
            reverseX = true;
            reverse = true;
        }
        if (startY - radius <= 0) {
            startY = radius;
            reverseY = true;
            reverse = true;
        }

        if (startX + radius >= width) {
            startX = width - radius;
            reverseX = true;
            reverse = true;
        }
        if (startY + radius >= height) {
            startY = height - radius;
            reverseY = true;
            reverse = true;
        }
        return reverse;
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startX += speedX * 3 / 100;
            startY += speedY * 3 / 100;
            speedX *= 0.95f;
            speedY *= 0.95f;

            if (fixLocation()) {
                if (reverseX) {
                    speedX = -speedX;
                }
                if (reverseY) {
                    speedY = -speedY;
                }
            }

            invalidate();

            if (speedX == 0 && speedY == 0) {
                handler.removeCallbacks(runnable);
                return;
            }
            handler.postDelayed(runnable, 30);
        }
    };
}
