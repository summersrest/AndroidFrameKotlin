package com.sum.base.utils

import com.sum.base.SFrame.Companion.context


/**
 * @author  LiuJiang
 * Desc:    屏幕工具类
 */
class ScreenUtils private constructor(){
    companion object {
        /**
         * 获取屏幕宽度
         */
        fun getScreenWidth(): Int {
            return context.resources.displayMetrics.widthPixels
        }

        /**
         * 获取屏幕高度
         */
        fun getScreenHeight(): Int {
            return context.resources.displayMetrics.heightPixels
        }

        /**
         * px转dp
         */
        fun px2dp(pxValue: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale).toInt()
        }


        /**
         * dp转px
         */
        fun dp2px(dipValue: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale).toInt()
        }

    }
}