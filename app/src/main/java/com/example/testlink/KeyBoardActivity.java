package com.example.testlink;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testlink.utils.ScreenUtils;

import io.reactivex.annotations.Nullable;

public class KeyBoardActivity extends Activity {

    private LinearLayout llFunctionList;
    private ImageView ivMore;
    private EditText etKeyBoard;
    LinearLayout llContent;

    private TextView tvPhoto, tvCamera;

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        ivMore = findViewById(R.id.ivMore);
        llFunctionList = findViewById(R.id.llFunctionList);
        etKeyBoard = findViewById(R.id.etKeyBoard);
        llContent = findViewById(R.id.llContent);
        tvPhoto = findViewById(R.id.tvPhoto);
        tvCamera = findViewById(R.id.tvCamera);


        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ivMore.setOnClickListener(v -> {
            //
            if (llFunctionList.getVisibility() == View.VISIBLE) {
                lockContentViewHeight();
                hideEmojiPanel(true);
                unlockContentViewHeight();
            } else {
                if (isSoftKeyboardShown()) {
                    lockContentViewHeight();
                    showEmojiPanel();
                    unlockContentViewHeight();
                } else {
                    showEmojiPanel();
                }
            }

        });

        tvCamera.setOnClickListener(v -> {
            Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
        });

        tvPhoto.setOnClickListener(v -> {
            Toast.makeText(this, "图片", Toast.LENGTH_SHORT).show();
        });

        etKeyBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP
                        && llFunctionList.getVisibility() == View.VISIBLE) {
                    lockContentViewHeight();
                    hideEmojiPanel(true);
                    unlockContentViewHeight();
                }
                return false;
            }
        });


        // 显示键盘
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(true);
            }
        }, 200);
    }

    /**
     * 锁定内容View以防止跳闪
     */
    private void lockContentViewHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llContent.getLayoutParams();
        layoutParams.height = llContent.getHeight();
        layoutParams.weight = 0;
    }

    /**
     * 释放锁定的内容View
     */
    private void unlockContentViewHeight() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) llContent.getLayoutParams()).weight = 1;
            }
        }, 200);
    }

    /**
     * 隐藏表情面板，同时指定是否随后开启键盘
     */
    private void hideEmojiPanel(boolean showSoftKeyboard) {

        llFunctionList.setVisibility(View.GONE);
        if (showSoftKeyboard) {
            showSoftKeyboard(false);
        }

    }

    /**
     * 显示表情面板
     */
    private void showEmojiPanel() {
        int softKeyboardHeight = getSoftKeyboardHeight();
        //没有显示键盘，获取上次保存的值
        if (softKeyboardHeight == 0) {
            softKeyboardHeight = getSoftKeyboardHeightLocalValue();
        } else {
            hideSoftKeyboard();
        }
        llFunctionList.getLayoutParams().height = softKeyboardHeight;
        llFunctionList.setVisibility(View.VISIBLE);

    }

    /**
     * 获取本地存储的键盘高度值或者是返回默认值
     */
    private int getSoftKeyboardHeightLocalValue() {
        return 200;
    }

    /**
     * 隐藏键盘
     */
    private void hideSoftKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(etKeyBoard.getWindowToken(), 0);
    }

    /**
     * 判断是否显示了键盘
     */
    private boolean isSoftKeyboardShown() {
        return getSoftKeyboardHeight() != 0;
    }


    private int getSoftKeyboardHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //屏幕当前可见高度，不包括状态栏
        int displayHeight = rect.bottom - rect.top;
        //屏幕可用高度
        int availableHeight = ScreenUtils.getAvailableScreenHeight(this);
        //用于计算键盘高度
        int softInputHeight = availableHeight - displayHeight - ScreenUtils.getStatusBarHeight(this);
        Log.e("lxx", "屏幕当前可见高度: " + displayHeight + "");
        Log.e("lxx", "屏幕可用高度: " + availableHeight + "");
        Log.e("lxx", "键盘高度: " + softInputHeight + "");
        if (softInputHeight != 0) {
            // 因为考虑到用户可能会主动调整键盘高度，所以只能是每次获取到键盘高度时都将其存储起来
//            sharedPreferences.edit().putInt(KEY_SOFT_KEYBOARD_HEIGHT, softInputHeight).apply();

        }
        return softInputHeight;
    }


    private void showSoftKeyboard(boolean saveSoftKeyboardHeight) {

        etKeyBoard.setFocusable(true);
        etKeyBoard.setFocusableInTouchMode(true);
        etKeyBoard.requestFocus();

        inputMethodManager.showSoftInput(etKeyBoard, 0);

        if (saveSoftKeyboardHeight) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSoftKeyboardHeight();
                }
            }, 200);
        }
    }
}
