package com.sum.framekt.base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.sum.framekt.application.App

/**
 * @author  liujiang
 * Desc:    工具类
 */
class WorkUtils {
    companion object {
        /**
         * 获取drawable资源
         *
         * @param resId
         * @return
         */
        fun getDrawable(resId: Int): Drawable? {
            return ContextCompat.getDrawable(App.instance(), resId)
        }

        /**
         * 获取字符串资源
         *
         * @param resId
         * @return
         */
        fun getString(resId: Int): String? {
            return App.instance().resources.getString(resId)
        }

        /**
         * 获取color资源
         *
         * @param resId
         * @return
         */
        fun getColor(resId: Int): Int {
            return ContextCompat.getColor(App.instance(), resId)
        }

        /**
         * 根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘
         */
        fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
            if (v != null && v is EditText) {
                val leftTop = intArrayOf(0, 0)
                //获取输入框当前的location位置
                v.getLocationInWindow(leftTop)
                val left = leftTop[0]
                val top = leftTop[1]
                val bottom = top + v.getHeight()
                val right = left + v.getWidth()
                return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
            }
            return false
        }

        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }
}