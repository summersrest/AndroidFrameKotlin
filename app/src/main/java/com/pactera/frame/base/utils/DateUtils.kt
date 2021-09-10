package com.pactera.frame.base.utils

import java.util.*

/**
 * @author liujiang
 * Desc: Date工具类
 */
object DateUtils {
    /**
     * 得到当前时间戳
     *
     * @return yyyy-MM-dd HH:mm:ss格式的当前时间
     */
    fun getCurrentStamps(): Long {
        return Date().time
    }
}