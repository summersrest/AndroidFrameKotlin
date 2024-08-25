package com.sum.frame.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 * @author  LiuJiang
 * Desc:    隐藏与显示软键盘
 */
class KeyboardUtils {
    companion object {

        /**
         * 显示软键盘
         * @param context   Activity的上下文
         * @param view      获取焦点的组件，必须是EditText, 或者EditText的子类，必须是可以获取焦点的, 且已经获取到焦点，必须是可见的，
         *                  必须已经完成加载，如果还未绘制完成，则showSoftInput()方法不起作用
         */
        @JvmStatic
        fun showKeyboard(context: Context, view: EditText?) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN)
        }

        /**
         * 隐藏软键盘
         * @param context   Activity的上下文
         * @param view      可以是布局中的任意一个view，也可以使用Activity的顶层来隐藏当前Activity中显示的软键盘
         */
        @JvmStatic
        fun hideKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_SHOWN)
        }

    }

}