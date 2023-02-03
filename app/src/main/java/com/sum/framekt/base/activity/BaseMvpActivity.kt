package com.sum.framekt.base.activity

import androidx.viewbinding.ViewBinding
import com.sum.framekt.base.http.HttpUtils
import com.sum.framekt.base.mvp.BasePresenter
import com.sum.framekt.base.mvp.BaseView
import com.sum.framekt.base.utils.ToastUtils
import com.sum.multiple.MultipleStatusView

/**
 * @author  liujiang
 * Desc:
 */
abstract class BaseMvpActivity<V : ViewBinding, P : BasePresenter<*, *>> : BaseActivity<V>(), BaseView {
    protected var presenter: P? = lazy { createPresenter() }.value

    abstract fun createPresenter(): P

    /**
     * 获取MultipleStatusView
     */
    open fun getMultipleStatusView(): MultipleStatusView? = null

    /**
     * 显示toast
     */
    override fun showToast(msg: String?) {
        ToastUtils.showShort(msg)
    }

    /**
     * 显示进度条弹窗
     */
    override fun showProgressDialog(msg: String?) {
        showDialog(msg)
    }

    /**
     * 更新进度条文字
     */
    override fun updateProgressText(msg: String?) {
        updateDialog(msg)
    }

    /**
     * 隐藏进度条弹窗
     */
    override fun hideProgressDialog(msg: String?) {
        hideDialog(msg)
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
    override fun showError(msg: String?) {
        getMultipleStatusView()?.showError()
    }

    /**
     * 加载空页面布局
     */
    override fun showEmpty() {
        getMultipleStatusView()?.showEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消网络请求
        HttpUtils.instance.cancelByTag(context)
        //断开presenter链接，防止内存泄漏
        presenter?.run {
            detach()
            presenter = null
        }
    }
}