package com.pactera.frame.base.pojo

/**
 * @author liujiang
 * created at: 2021/9/9 10:38
 * Desc:
 */
data class BasePojo<T>(
    val code: Int,
    val message: String,
    val success: Boolean,
    val timestamp: Long,
    val result : T
)

