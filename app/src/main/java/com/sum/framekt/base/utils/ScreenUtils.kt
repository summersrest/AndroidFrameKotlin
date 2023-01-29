package com.sum.framekt.base.utils

import android.app.Activity
import android.content.Context
import android.hardware.display.DisplayManager
import android.util.DisplayMetrics
import android.view.Display
import com.sum.framekt.application.App

/**
 * @author  LiuJiang
 * Desc:    获得屏幕相关的辅助类
 */
class ScreenUtils {
    companion object {
        /**
         * 获得屏幕高度
         */
        fun getScreenWidth(): Int {
            val displayManager = App.instance().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
            val outMetrics = DisplayMetrics()
            displayManager.getDisplay(Display.DEFAULT_DISPLAY).getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * 获得屏幕宽度
         */
        fun getScreenHeight(): Int {
            val displayManager = App.instance().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
            val outMetrics = DisplayMetrics()
            displayManager.getDisplay(Display.DEFAULT_DISPLAY).getMetrics(outMetrics)
            return outMetrics.heightPixels
        }

        /**
         * 获得屏幕实际宽度
         */
        fun getRealScreenWidth(context: Context): Int {
            val displayManager = App.instance().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
            val outMetrics = DisplayMetrics()
            displayManager.getDisplay(Display.DEFAULT_DISPLAY).getRealMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * 获得屏幕实际高端
         */
        fun getRealScreenHeight(activity: Activity): Int {
            val displayManager = App.instance().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
            val outMetrics = DisplayMetrics()
            displayManager.getDisplay(Display.DEFAULT_DISPLAY).getRealMetrics(outMetrics)
            return outMetrics.heightPixels
        }
    }
}