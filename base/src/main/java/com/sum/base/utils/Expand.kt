package com.sum.base.utils

import android.graphics.drawable.Drawable
import android.text.InputType
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.sum.base.SFrame.Companion.context


/**
 * EditText判空
 */
fun EditText.isEmpty(msg: String?): String? {
    val text = text.toString()
    if (text.isBlank()) {
        ToastUtils.show(msg?.takeIf { it.isNotBlank() } ?: "输入内容不可为空")
        return null
    }
    return text
}

/**
 * EditText取值
 */
fun EditText.getContent(): String = this.text.toString()

/**
 * EditText设置是否可编辑
 */
fun EditText.setEnable(enable: Boolean) {
    this.isFocusable = enable
    this.setFocusableInTouchMode(enable)
    this.isLongClickable = enable
    this.setInputType(if (enable) InputType.TYPE_CLASS_TEXT else InputType.TYPE_NULL)
}

/**
 * 从Res获取图片
 */
fun Int.getDrawable(): Drawable? = ContextCompat.getDrawable(context, this)

/**
 * 从Res获取字符串
 */
fun Int.getString(): String = ContextCompat.getString(context, this)

/**
 * 从Res获取色值
 */
fun Int.getColor(): Int = ContextCompat.getColor(context, this)