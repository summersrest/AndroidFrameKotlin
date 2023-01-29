package com.sum.framekt.base.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.sum.framekt.R
import com.sum.framekt.base.pojo.EventMessage
import com.sum.framekt.base.utils.FastDoubleClick
import com.sum.framekt.base.utils.ToastUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author  liujiang
 * Desc:    基类
 */
abstract class BaseFragment<V : ViewBinding> : Fragment(), OnClickListener {
    private var loadDialog: LoadingPopupView? = null

    protected abstract fun getBinding(): V

    protected abstract fun initView()

    protected lateinit var activity: AppCompatActivity

    protected lateinit var viewBinding: V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        Type superclass = getClass().getGenericSuperclass();
//        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
//        try {
//            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class,ViewGroup.class,boolean.class);
//            viewBinding = (V) method.invoke(null, getLayoutInflater(),container,false);
//        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
//            e.printStackTrace();
//        }
        viewBinding = getBinding()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * eventbus接收者
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventMessage<Any?>) {
        onEvent(event)
    }

    /**
     *复写此方法接收eventBus消息
     */
    protected open fun onEvent(event: EventMessage<Any?>) {}

    /**
     * 点击事件
     */
    override fun onClick(v: View?) {
        if (FastDoubleClick.isFastDoubleClick()) {
            return
        }
        onClickEvent(v)
    }

    /**
     * 点击事件，复写此方法
     */
    protected open fun onClickEvent(v: View?) {}

    /**
     * 弹出进度条弹窗
     */
    protected fun showDialog(msg: String? = resources.getString(R.string.loading)) {
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
     * 隐藏进度条
     */
    protected fun hideDialog(msg: String? = null) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg)
        }
        loadDialog?.dismiss() ?: kotlin.run { loadDialog = null }
    }

    /**
     * 更新进度条弹窗颜色
     */
    protected fun updateDialog(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            loadDialog?.setTitle(msg)
        }
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