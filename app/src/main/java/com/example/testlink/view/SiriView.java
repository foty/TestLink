package com.example.testlink.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lxx on 2019/9/9.
 * Use by
 */

public class SiriView extends View {

    private Paint paint;
    private float[] waveWidth;
    private float[] waveAlpha;
    private float[] waveAmplitude;
    private int[] wavePhase = {0, 6, -9, 15, -21};
    private float phase = 1;
    private ValueAnimator animator;


    public SiriView(Context context) {
        this(context, null, 0);
    }

    public SiriView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SiriView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化画笔
        paint = new Paint();
        paint.setStrokeWidth(1);// 画线宽度
        paint.setStyle(Paint.Style.STROKE);//空心效果
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.BLACK);

        waveWidth = new float[]{3, 2, 2, 1, 1};
        waveAlpha = new float[]{1.0f, 0.8f, 0.6f, 0.4f, 0.2f};
        waveAmplitude = new float[]{1.0f, 0.7f, 0.4f, 0.1f, -0.2f};
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float period = 2.0f;// 区域内，正弦波的周期

        // 将绘图原点移动到区域中心
        int width = getWidth();
        int height = getHeight();
        float midWidth = width / 2.0f;
        float midHeight = height / 2.0f;
        canvas.translate(midWidth, midHeight);

        for (int i = 0; i < waveAmplitude.length; i++) {
            // 初始化线条
            Path sinPath = new Path();
            paint.setStrokeWidth(waveWidth[i]);//画笔宽度
            paint.setAlpha((int) (waveAlpha[i] * 255));//画笔透明度

            sinPath.reset();//重置线条
            sinPath.moveTo(-midWidth, 0);


            // 计算线条
            for (float x = -midWidth; x < midWidth; x++) {
                double scaling = 1 - Math.pow(x / midWidth, 2);

                double sine = Math.sin(2 * Math.PI * period * (phase + wavePhase[i] + x / width));//计算该点上的正弦值
                float y = (float) (midHeight * sine * scaling * waveAmplitude[i]);// 将正弦值限定到绘图区的高度上
                sinPath.lineTo(x, y);
            }

            canvas.drawPath(sinPath, paint);//绘制线条
        }

//        canvas.restore();
    }

    public void nextPhase(float n) {
        phase -= 0.07;
        if (phase == 0) {
            //phase = 1;
        }
        invalidate();
    }


    private int time = 5;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time > 0) {
                nextPhase(0);
                handler.postDelayed(runnable, 100);
                time--;
            } else {
                time = 5;
                handler.postDelayed(runnable, 100);
            }

        }
    };

    private Handler handler = new Handler();

    public void start() {

        handler.post(runnable);


//        animator = ValueAnimator.ofFloat(0, 1f);
//        animator.setDuration(10000);
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setInterpolator(null);//时间插值器
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float f = (float) animation.getAnimatedValue();
//                Log.d("LXX", "改变的值  " + f);
//                nextPhase(f);
//            }
//        });
//        animator.start();
    }
}
