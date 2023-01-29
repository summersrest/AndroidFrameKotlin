package com.sum.framekt.base.dialog.formatter

/**
 * @author  LiuJiang
 * created  at: 2023/1/22 12:49
 * Desc:
 */
interface Formatter<T> {
    fun format(item: T?): String?
}