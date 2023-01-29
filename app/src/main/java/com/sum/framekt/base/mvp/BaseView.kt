package com.sum.framekt.base.mvp

/**
 * @author  liujiang
 * created  at: 2023/1/20 16:51
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