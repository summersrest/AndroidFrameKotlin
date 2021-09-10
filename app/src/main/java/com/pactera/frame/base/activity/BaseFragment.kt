package com.pactera.frame.base.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pactera.frame.R
import com.pactera.frame.base.dialog.LoadingDialog
import com.pactera.frame.base.pojo.EventMessage
import com.pactera.frame.base.utils.FastDoubleClick
import com.pactera.frame.base.utils.ScreenUtils
import com.pactera.frame.base.utils.ToastUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author liujiang
 * Desc: 基类
 */
abstract class BaseFragment<V : ViewBinding> : Fragment(), View.OnClickListener {
    private var loadDialog: LoadingDialog? = null

    protected var activity: AppCompatActivity? = null

    protected var viewBinding: V? = null

    protected abstract fun initViewBinding(): V

    protected abstract fun initView(savedInstanceState: Bundle)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val type = javaClass.genericSuperclass
//        if (type is ParameterizedType) {
//            val clazz = type.actualTypeArguments[0] as Class<V>
//            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
//            viewBinding = method.invoke(null, layoutInflater) as V
//        }

        viewBinding = initViewBinding()
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState!!)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: EventMessage<Any>) {
        onEvent(event)
    }

    /**
     * 复写此方法接收eventBus消息
     * @param event
     */
    protected open fun onEvent(event: EventMessage<Any>) {}

    /**
     * 弹出进度条弹窗
     * @param msg
     */
    protected open fun showDialog(msg: String? = null) {
        if (msg != null && msg.isNotEmpty()) {
            setDialog(msg)
        } else {
            setDialog(resources.getString(R.string.loading))
        }
    }

    /**
     * 隐藏进度条弹窗
     *
     * @param msg
     */
    protected open fun hintDialog(msg: String? = null) {
        if (msg != null) {
            if (msg.isNotEmpty()) {
                ToastUtils.showShort(msg)
            }
        }
        // 取消加载对话框
        loadDialog?.cancel()
        loadDialog = null
    }

    /**
     * 设置进度条
     */
    private fun setDialog(text: String) {
        // 正在加载对话框
        if (loadDialog == null) {
            loadDialog = getActivity()?.let { LoadingDialog(it, text) }
        } else {
            loadDialog?.setText(text)
        }
        loadDialog?.window?.setDimAmount(0.4f)
        loadDialog?.show()
        val params = loadDialog?.window?.attributes
        params?.width = ScreenUtils.getScreenWidth(getActivity()) / 2
        params?.height = RelativeLayout.LayoutParams.WRAP_CONTENT
        loadDialog?.window?.attributes = params
    }

    override fun onClick(v: View) {
        //防止重复点击
        if (FastDoubleClick.isFastDoubleClick()) {
            return
        }
        onClickEvent(v)
    }

    /**
     * 点击事件，复写此方法
     */
    protected fun onClickEvent(v: View) {}
}