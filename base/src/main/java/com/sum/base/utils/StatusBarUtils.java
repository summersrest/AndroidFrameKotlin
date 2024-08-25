package com.sum.base.utils;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

/**
 * @author  LiuJiang
 * Desc:    状态栏工具类
 */
public class StatusBarUtils {

    /**
     * 设置沉浸式状态栏
     *
     * @param isLightStatusBar 是否浅色主题，浅色主题状态栏字体颜色为黑色。非浅色主题状态栏字体颜色为白色。
     *                         true：状态栏字体黑色
     *                         false：状态栏字体白色
     */
    public void setTranslucentStatusBar(AppCompatActivity activity, boolean isLightStatusBar) {
        //取消设置状态栏全透明
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        //状态栏字体是否修改为黑色
        if (isLightStatusBar) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        //将View延伸到状态栏
        ViewGroup mContentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(false);
            ViewCompat.requestApplyInsets(mChildView);
        }

    }
}
