package com.sum.framekt.base.pojo

/**
 * @author  LiuJiang
 * created  at: 2023/1/24 16:20
 * Desc:
 */
data class PagePojo<T>(var records: MutableList<T>, var total: Int, var size: Int, var current: Int)
