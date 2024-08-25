package com.sum.frame.entity

/**
 * EventBus实例
 */
data class EventMessage(var code: Int, var message: Any? = null)

/**
 * 网络请求异常
 */
data class HttpException(var code: Int, var msg: String? = "未知异常") : Exception()