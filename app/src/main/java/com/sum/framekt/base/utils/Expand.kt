package com.sum.framekt.base.utils

import android.widget.EditText

/**
 * EditText判空
 */
fun EditText.isEmpty(msg: String?): String? {
    val text = text.toString()
    if (text.isBlank()) {
        ToastUtils.showShort(msg?.takeIf { it.isNotBlank() } ?: "输入有问题")
        return null
    }
    return text
}

/**
 * EditText取值
 */
fun EditText.getContent(): String {
    return this.text.toString()
}