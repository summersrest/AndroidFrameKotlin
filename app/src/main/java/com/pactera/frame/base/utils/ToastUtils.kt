package com.pactera.frame.base.utils

import android.widget.Toast
import com.pactera.frame.base.App

/**
 * @author liujiang
 * Desc: toast封装
 */
object ToastUtils {

    var isShow = true

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    fun showShort(message: CharSequence?) {
        if (isShow) Toast.makeText(App.instance(), message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    fun showLong(message: CharSequence?) {
        if (isShow) Toast.makeText(App.instance(), message, Toast.LENGTH_LONG).show()
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    fun show(message: CharSequence?, duration: Int) {
        if (isShow) Toast.makeText(App.instance(), message, duration).show()
    }
}