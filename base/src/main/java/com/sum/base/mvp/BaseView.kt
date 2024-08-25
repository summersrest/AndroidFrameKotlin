package com.sum.base.mvp

/**
 * @author  LiuJiang
 * Desc:
 */
interface BaseView {
    fun showToast(msg: String?)

    fun showProgressDialog(msg: String? = "")

    fun updateProgressText(msg: String?)

    fun hideProgressDialog(msg: String? = "")

    fun showLoading()

    fun showContent()

    fun showError(msg: String? = "")

    fun showEmpty()
}