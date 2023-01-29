package com.sum.framekt.base.utils

/**
 * @author  liujiang
 * Desc:    防止重复点击
 */
class FastDoubleClick {
    companion object{
        private var lastClickTime: Long = 0
        fun isFastDoubleClick(): Boolean {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (timeD in 0..1000) {
                return true
            }
            lastClickTime = time
            return false
        }
    }
}