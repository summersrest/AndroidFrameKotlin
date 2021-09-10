package com.pactera.frame.base.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.pactera.frame.R
import com.pactera.frame.base.dialog.LoadingDialog
import com.pactera.frame.base.pojo.EventMessage
import com.pactera.frame.base.utils.FastDoubleClick
import com.pactera.frame.base.utils.ScreenUtils
import com.pactera.frame.base.utils.ToastUtils
import com.pactera.frame.base.utils.WorkUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author liujiang
 * Desc: 基类
 */
abstract class BaseActivity<V : ViewBinding> : PermissionActivity(), View.OnClickListener {
    private var loadDialog: LoadingDialog? = null

    protected var activity: AppCompatActivity? = null

    protected var context: Context? = null

    protected lateinit var viewBinding: V

    protected abstract fun initViewBinding(): V

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val type = javaClass.genericSuperclass
//        if (type is ParameterizedType) {
//            val clazz = type.actualTypeArguments[0] as Class<V>
//            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
//            viewBinding = method.invoke(null, layoutInflater) as V
//            setContentView(viewBinding?.root)
//        }

        viewBinding = initViewBinding()
        setContentView(viewBinding?.root)

        context = this
        activity = this
        //设置沉浸式状态栏
        setStatusBar()
        initView(savedInstanceState)
    }

    /**
     * @author liujiang
     * Desc: 设置状态栏
     */
    protected fun setStatusBar() {
        //        ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .autoNavigationBarDarkModeEnable(true,0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
//                .flymeOSStatusBarFontColor(R.color.btn3)  //修改flyme OS状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
//                .titleBarMarginTop(view)     //解决状态栏和布局重叠问题，任选其一
//                .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
//                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
//                .supportActionBar(true) //支持ActionBar使用
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
//                .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为true
//                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
//                .navigationBarWithEMUI3Enable(true) //是否可以修改emui3.x手机导航栏颜色，默认为true
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
//                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调，keyboardEnable为true才会回调此方法
//                    @Override
//                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
//                        LogUtils.e(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
//                    }
//                })
//                .setOnNavigationBarListener(onNavigationBarListener) //导航栏显示隐藏监听，目前只支持华为和小米手机
//                .setOnBarListener(OnBarListener) //第一次调用和横竖屏切换都会触发，可以用来做刘海屏遮挡布局控件的问题
//                .addTag("tag")  //给以上设置的参数打标记
//                .getTag("tag")  //根据tag获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
//                .init();  //必须调用方可应用以上所配置的参数

        //使用ImmersionBar的fitsSystemWindows(boolean fits)方法，只适合纯色状态栏
        ImmersionBar.with(activity!!)
            .fitsSystemWindows(true) //防止状态栏与布局顶部重叠
            .statusBarColor(R.color.app_color) //使用该属性,必须指定状态栏颜色
            .autoStatusBarDarkModeEnable(true, 0.2f)
            .init()
    }

    /**
     * 设置标题栏
     * @param titleLayout
     * @param title
     */
    protected fun setTitle(titleLayout: RelativeLayout, title: String?) {
        //返回按钮
        val backBtn = titleLayout.findViewById<LinearLayout>(R.id.btn_back)
        backBtn.setOnClickListener { onActivityFinish() }
        //标题
        val titleTv = titleLayout.findViewById<TextView>(R.id.tv_title)
        titleTv.text = title
        //右侧按钮
        val actionTv = titleLayout.findViewById<TextView>(R.id.tv_action)
        actionTv.visibility = View.GONE
    }

    /**
     * 在关闭activity的同时需要进行一些操作，可复写该方法
     */
    protected fun onActivityFinish() {
        finish()
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
            loadDialog = LoadingDialog(this, text)
        } else {
            loadDialog?.setText(text)
        }
        loadDialog?.window?.setDimAmount(0.4f)
        loadDialog?.show()
        val params = loadDialog?.window?.attributes
        params?.width = ScreenUtils.getScreenWidth(this) / 2
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

    /**
     * 设置点击软键盘以外的地方，软键盘消失
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (WorkUtils.isShouldHideInput(v, ev)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
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

    override fun onBackPressed() {
        onActivityFinish()
    }

}