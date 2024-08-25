package com.sum.base.mvp

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.sum.base.entity.EventMessage
import com.sum.base.http.HttpUtils
import com.sum.base.utils.DoubleClickUtils
import com.sum.base.utils.ToastUtils
import com.sum.multiple.MultipleStatusView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @author  LiuJiang
 * Desc:
 */
abstract class BaseFragment<V : ViewBinding> : Fragment(), View.OnClickListener {
    private var loadDialog: LoadingPopupView? = null

    protected lateinit var viewBinding: V

    protected lateinit var activity: AppCompatActivity

    protected abstract fun initView()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parameterizedType: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val types = parameterizedType.actualTypeArguments
        val aClass = types[0] as Class<*>
        val method: Method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        viewBinding = method.invoke(null, layoutInflater) as V
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    /**
     * EventBus
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventMessage) {
        onEvent(event)
    }


    /**
     * EventBus接收者
     */
    protected open fun onEvent(event: EventMessage) {

    }

    /**
     * 防双击
     */
    override fun onClick(v: View) {
        if (DoubleClickUtils.isDoubleClick) return
        onClickEvent(v)

    }

    /**
     * 点击事件，复写此方法
     */
    protected open fun onClickEvent(v: View) {}


    /**
     * 弹出全屏进度条
     */
    protected fun showDialog(msg: String? = "加载中，请稍候…") {
        loadDialog?.show() ?: run {
            loadDialog = XPopup.Builder(context)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .isLightNavigationBar(true)
                .asLoading(msg)
                .show() as LoadingPopupView
        }
    }

    /**
     * 更新进度条弹窗文字
     */
    protected fun updateDialog(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            loadDialog?.setTitle(msg)
        }
    }

    /**
     * 隐藏进度条
     */
    protected fun hideDialog(msg: String? = null) {
        ToastUtils.show(msg)
        loadDialog?.dismiss() ?: kotlin.run { loadDialog = null }
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}


/**
 * @author  LiuJiang
 * Desc:
 */
abstract class BaseMvpFragment<V : ViewBinding, P : BasePresenter<*, *>> : BaseFragment<V>(), BaseView {
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
        ToastUtils.show(msg)
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
        HttpUtils.instance.cancelByTag(this)
        //断开presenter链接，防止内存泄漏
        presenter?.run {
            detach()
            presenter = null
        }
    }
}