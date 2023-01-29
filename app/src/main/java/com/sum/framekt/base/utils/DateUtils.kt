package com.sum.framekt.base.utils

import java.util.*

/**
 * @author  LiuJiang
 * Desc:    Date工具类
 */
class DateUtils {
    companion object {
        /**
         * 获取当前时间戳
         */
        fun getCurrentStamps(): Long {
            return Date().time
        }
    }
}