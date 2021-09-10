package com.pactera.frame.base.utils

/**
 * @author liujiang
 * created at: 2021/9/9 10:15
 * Desc:
 */
object FastDoubleClick {
    private var lastClickTime: Long = 0

    fun isFastDoubleClick(): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime
        if (timeD in 1..999) {
            return true
        }
        lastClickTime = time
        return false
    }
}