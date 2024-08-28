package com.sum.frame.mvp

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.sum.frame.entity.EventMessage
import com.sum.frame.http.HttpUtils
import com.sum.frame.utils.DoubleClickUtils
import com.sum.frame.utils.ToastUtils
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
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity(), View.OnClickListener {
    private var loadDialog: LoadingPopupView? = null

    protected lateinit var viewBinding: V

    protected lateinit var context: Context

    protected lateinit var activity: AppCompatActivity

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activity = this
        val parameterizedType: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val types = parameterizedType.actualTypeArguments
        val aClass = types[0] as Class<*>
        val method: Method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        viewBinding = method.invoke(null, layoutInflater) as V
        setContentView(viewBinding.root)
        initView(savedInstanceState)
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
    fun showDialog(msg: String? = "加载中，请稍候…") {
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
    fun updateDialog(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            loadDialog?.setTitle(msg)
        }
    }

    /**
     * 隐藏进度条
     */
    fun hideDialog(msg: String? = null) {
        ToastUtils.show(msg)
        if (loadDialog?.isDismiss == false) {
            loadDialog?.dismiss() ?: kotlin.run { loadDialog = null }
        }
    }

    /**
     * 根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    /**
     * 点击坐标是否在某个控件的范围以内
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideDialog()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}


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