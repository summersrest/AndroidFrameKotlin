package com.sum.sample.entity

/**
 * @author  LiuJiang
 * Desc:
 */
data class BaseEntity<T>(
    var error: Int = 0,
    var message: String = "",
    var data: T? = null
)
