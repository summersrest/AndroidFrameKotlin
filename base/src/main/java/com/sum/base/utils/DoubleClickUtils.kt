package com.sum.base.utils

/**
 * @author  LiuJiang
 * Desc:    防连点
 */
class DoubleClickUtils private constructor() {
    companion object {
        private var lastClickTime: Long = 0

        /**
         * 是否快速点击
         */
        @JvmStatic
        val isDoubleClick: Boolean
            get() {
                val clickTime = System.currentTimeMillis()
                if (clickTime - lastClickTime > 1000) {
                    lastClickTime = clickTime
                    return false
                }
                return true
            }
    }
}