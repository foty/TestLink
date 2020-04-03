package com.example.testlink.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.testlink.R;

/**
 * Created by lxx on 2019/4/8.
 * Use by 非完全自定义view
 */

public class FirstView extends ViewGroup {
    private Paint paint;


    public FirstView(Context context) {
        super(context);
        initView(context);
    }


    public FirstView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));

         ImageView view = new ImageView(context);
        view.setImageResource(R.mipmap.ic_launcher);
         addView(view);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

        Log.d("lxx", "onMeasure:1 " + getSuggestedMinimumWidth());
        Log.d("lxx", "onMeasure:2 " + getSuggestedMinimumHeight());

        Log.d("lxx", "onMeasure:3 " + MeasureSpec.getSize(widthMeasureSpec));
        Log.d("lxx", "onMeasure:4 " + MeasureSpec.getSize(heightMeasureSpec));


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);


        measureChildren(widthMeasureSpec,heightMeasureSpec);

//        int width = getMySize(100, widthMeasureSpec);
//        int height = getMySize(100, heightMeasureSpec);
//
//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }
//
//        setMeasuredDimension(width, height);





        /**
         * AT_MOST ==> wrap_content
         * EXACTLY ==> 固定值，如100dp / match_parent
         * UNSPECIFIED ==>
         */

        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                Log.d("lxx", "width onMeasure: MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.d("lxx", "width onMeasure: MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d("lxx", "width onMeasure: MeasureSpec.UNSPECIFIED");
                break;
        }

        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                Log.d("lxx", "height onMeasure: MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.d("lxx", "height onMeasure: MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d("lxx", "height onMeasure: MeasureSpec.UNSPECIFIED");
                break;
        }

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
//        setMeasuredDimension(widthMeasureSpec,270);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("lxx", "改变大小了" + w + "   " + h + "   " + oldh + "   " + oldw);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            child.layout(10, 20, 150, 100);
//        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getMeasuredHeight();
        Log.d("lxx", "ondraw : height = " + height);
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
        Log.d("lxxxx", "getDefaultWidth: " + outMetrics.widthPixels + "-------" + outMetrics.heightPixels);
        return Math.min(outMetrics.widthPixels, outMetrics.heightPixels);
    }

    /**
     * 测量实际大小
     *
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    private int getMySize(int defaultSize, int measureSpec) {

        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }


}
