package com.sum.frame.mvp

/**
 * @author  LiuJiang
 * Desc:
 */
abstract class BasePresenter<M : BaseModel, V : BaseView>(var tag: Any?, var view: V?) {
    protected var model: M? = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { createMode() }.value

    abstract fun createMode(): M


    /**
     * Activity的onDestroy()中调用，端口链接防止内存泄漏
     */
    fun detach() {
        view = null
        model = null
        tag = null
    }
}