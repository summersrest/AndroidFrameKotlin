package com.sum.framekt.base.utils

import android.text.TextUtils
import android.widget.Toast
import com.sum.framekt.application.App

/**
 * @author  LiuJiang
 * created  at: 2023/1/20 14:46
 * Desc:
 */
class ToastUtils {
    companion object {
        fun showShort(message: CharSequence?) {
            show(message, Toast.LENGTH_SHORT)
        }

        fun showLong(message: CharSequence?) {
            show(message, Toast.LENGTH_LONG)
        }

        private fun show(message: CharSequence?, duration: Int) {
            message.takeUnless { TextUtils.isEmpty(message) }?.let {
                Toast.makeText(App.instance(), it, duration).show()
            }

        }
    }
}