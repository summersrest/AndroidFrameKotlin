package com.pactera.frame.base.mvp

/**
 * @author liujiang
 * Desc:
 */
interface BaseView {
    fun showToast(msg: String)

    fun showProgressDialog()

    fun showProgressDialog(msg: String)

    fun hideProgressDialog()

    fun hideProgressDialog(msg: String)

    fun showLoading()

    fun showContent()

    fun showError()

    fun showError(msg: String)

    fun showEmpty()
}