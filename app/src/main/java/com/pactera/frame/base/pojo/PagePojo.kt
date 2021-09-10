package com.pactera.frame.base.pojo

/**
 * @author liujiang
 * Desc: 分页对象
 */
data class PagePojo<T>(
    var records : List<T>,
    var total : Int,
    var size : Int,
    var current : Int
)
