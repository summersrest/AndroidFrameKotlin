package com.sum.framekt.base.mvp

/**
 * @author  liujiang
 * created  at: 2023/1/20 16:53
 * Desc:
 */
interface BaseRefreshView : BaseView {
    fun notifyAdapter()

    fun finishLoadMore()

    fun finishRefresh()

    fun loadMoreComplete()
}