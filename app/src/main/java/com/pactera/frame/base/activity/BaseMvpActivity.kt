package com.pactera.frame.base.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.pactera.frame.R
import com.pactera.frame.base.mvp.BasePresenter
import com.pactera.frame.base.mvp.BaseView
import com.pactera.frame.base.utils.ToastUtils
import com.sum.multiple.MultipleStatusView

/**
 * @author liujiang
 * Desc: 基类mvp
 */
abstract class BaseMvpActivity<V : ViewBinding, P : BasePresenter<*, *>> : BaseActivity<V>(), BaseView {
    protected lateinit var presenter: P
    abstract fun createPresenter(): P
    abstract override fun initView(savedInstanceState: Bundle?)
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    /**
     * @author liujiang
     * Desc: 获取MultipleStatusView
     */
    protected fun getMultipleStatusView(): MultipleStatusView? {
        return null
    }

    /**
     * 显示toast
     *
     * @param msg
     */
    override fun showToast(msg: String) {
        if (msg.isNotEmpty()) ToastUtils.showShort(msg)
    }

    /**
     * 显示进度条弹窗
     */
    override fun showProgressDialog() {
        showDialog(resources.getString(R.string.loading))
    }

    /**
     * 显示进度条弹窗
     *
     * @param msg
     */
    override fun showProgressDialog(msg: String) {
        showDialog(msg)
    }

    /**
     * 隐藏进度条弹窗
     */
    override fun hideProgressDialog() {
        hintDialog()
    }

    /**
     * 隐藏进度条弹窗
     *
     * @param msg
     */
    override fun hideProgressDialog(msg: String) {
        hintDialog(msg)
    }

    /**
     * 加载loading视图
     */
    override fun showLoading() {
        getMultipleStatusView()?.showLoading()
    }

    /**
     * 加载内容视图
     */
    override fun showContent() {
        getMultipleStatusView()?.showContent()
    }

    /**
     * 加载错误页面布局
     */
    override fun showError() {
        getMultipleStatusView()?.showEmpty()
    }

    /**
     * 加载错误页面布局
     *
     * @param msg
     */
    override fun showError(msg: String) {
        if (msg.isNotEmpty())
            ToastUtils.showShort(msg)
        getMultipleStatusView()?.showError()
    }

    /**
     * 加载空页面布局
     */
    override fun showEmpty() {
        getMultipleStatusView()?.showEmpty()
    }
}