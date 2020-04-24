package com.example.testlink.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.testlink.R;

public class CoinAnimView {
    private AnimationDrawable animationDrawable;
    private final double[] coinX = new double[]{2.53, -4.75, -3.45, 6.33, -2.92, 6.33, 6.33, -3.8, -9.5, -7.6, -3.8, -9.5, 3.45, -4.75, 5.42, -4.22, -2.53, 3.45, -19};
    private final double[] coinY = new double[]{4.87, 4.33, 4.1, 5.57, 4.58, 8.66, 7.8, -26, -15.6, -10.4, 6.5, -7.8, 26, 11.14, -11.14, -13, 26, 19.5, 19.5};

    private AnimatorSet animatorSet2 = new AnimatorSet();
    private static Handler handler = new Handler(Looper.getMainLooper());
    private int coinNum = 19;
    private OnAnimEndListener onAnimEndListener = null;

    private Context context;
    private int screenHeight;
    private int screenWidth;

    public CoinAnimView(Context context, int screenHeight, int screenWidth) {
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public synchronized CoinAnimView doAnim(final ViewGroup viewGroup) {
        for (int i = 0; i < coinNum; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ImageView ivCoin = new ImageView(context);
                    ViewGroup.LayoutParams layoutParams =
                            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                                    , ViewGroup.LayoutParams.WRAP_CONTENT);
                    ivCoin.setLayoutParams(layoutParams);
                    viewGroup.addView(ivCoin);
//                    ivCoin.setBackgroundResource(R.drawable.coinanim);
                    animationDrawable = (AnimationDrawable) ivCoin.getBackground();
                    animationDrawable.start();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivCoin, "scaleX"
                            , 0, 1).setDuration(800);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivCoin, "scaleY"
                            , 0, 1).setDuration(800);
                    ObjectAnimator translationX = ObjectAnimator.ofFloat(ivCoin, "translationX"
                            , 0, (float) (screenWidth / coinX[finalI])).setDuration(800);
                    translationX.setInterpolator(new AccelerateInterpolator());
                    ObjectAnimator translationY = ObjectAnimator.ofFloat(ivCoin, "translationY"
                            , 0, (float) (screenHeight / coinY[finalI])).setDuration(800);
                    translationY.setInterpolator(new AccelerateInterpolator());
                    //计算出最终的归宿位置
                    int startX = -screenWidth / 2 + 50;
                    int startY = -screenHeight / 2 + 200;
                    //转换为绝对值计算两点的X,Y轴距离
                    double v = Math.pow((Math.abs(startX) - Math.abs(screenWidth / coinX[finalI])), 2)
                            + Math.pow((Math.abs(startY) - Math.abs(screenWidth / coinY[finalI])), 2);
                    //Math.sqrt(v),根据勾股定理开方,计算两点的距离, 动态改变移动的时间
                    ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(ivCoin, "scaleX", 1.3f, 0f).setDuration((long) (Math.sqrt(v) * 3.5));
                    ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(ivCoin, "scaleY", 1.3f, 0f).setDuration((long) (Math.sqrt(v) * 3.5));
                    scaleX1.setInterpolator(new AccelerateDecelerateInterpolator());
                    scaleY1.setInterpolator(new AccelerateDecelerateInterpolator());
                    ObjectAnimator translationX1 = ObjectAnimator.ofFloat(ivCoin, "translationX"
                            , (float) (screenWidth / coinX[finalI]), startX).setDuration((long) (Math.sqrt(v) * 3));
                    ObjectAnimator translationY1 = ObjectAnimator.ofFloat(ivCoin, "translationY"
                            , (float) (screenHeight / coinY[finalI]), startY).setDuration((long) (Math.sqrt(v) * 3));
                    translationX1.setInterpolator(new AccelerateInterpolator());
                    translationY1.setInterpolator(new AccelerateInterpolator());
                    animatorSet2.playTogether(scaleX1, scaleY1, translationX1, translationY1);
                    animatorSet.playTogether(scaleX, scaleY, translationX, translationY);
                    animatorSet.start();
                    animationDrawable.start();
                }
            }, i * 40);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animatorSet2.start();
                }
            }, 1500);
        }
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimEndListener != null)
                    onAnimEndListener.onAnimEnd();
                animationDrawable.stop();
//                animatorSet.cancel();
//                animatorSet = null;
                animatorSet2.cancel();
                animatorSet2 = null;
                viewGroup.removeAllViews();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return this;
    }

    public CoinAnimView pauseAnim() {
//        if (animatorSet != null)
//            animatorSet.pause();
        if (animatorSet2 != null) {
            // TODO: 2020/4/16 api >= 19
//            animatorSet2.pause();
        }
        return this;
    }

    public CoinAnimView resumeAnim() {
//        if (animatorSet != null)
//            animatorSet.resume();
        if (animatorSet2 != null) {
            // TODO: 2020/4/16 api >= 19
//            animatorSet2.resume();
        }
        return this;
    }

    public interface OnAnimEndListener {
        void onAnimEnd();
    }

    public void setOnAnimEndListener(OnAnimEndListener onAnimEndListener) {
        this.onAnimEndListener = onAnimEndListener;
    }
}
