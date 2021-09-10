package com.pactera.frame.base.pojo

/**
 * @author liujiang
 * Desc:
 */
class EventMessage<T> {
    var event: Int
    var t: T? = null

    constructor(event: Int) {
        this.event = event
    }

    constructor(event: Int, t: T) {
        this.event = event
        this.t = t
    }

}
