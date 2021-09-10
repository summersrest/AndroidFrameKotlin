package com.pactera.frame.base.mvp

/**
 * @author liujiang
 * created at: 2021/9/9 16:23
 * Desc:
 */
interface BaseRefreshView : BaseView {
    fun notifyAdapter()
    fun finishLoadMore()
    fun finishRefresh()
    fun loadMoreComplete()
}