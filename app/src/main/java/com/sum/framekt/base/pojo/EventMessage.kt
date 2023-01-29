package com.sum.framekt.base.pojo

/**
 * @author  liujiang
 * created  at: 2023/1/20 13:10
 * Desc:
 */
data class EventMessage<T>(var event: Int, var message: T?) {
    constructor(event: Int) : this(event, null)
}
