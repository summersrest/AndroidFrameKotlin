package com.sum.framekt.base.mvp

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * @author  liujiang
 * Desc:
 */
abstract class BasePresenter<M : BaseModel, V : BaseView>(var listener: V?, var context: Context?) {
    protected var fragment: Fragment? = null
    abstract fun createModel(): M
    protected var model: M? = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { createModel() }.value

    constructor(listener: V, fragment: Fragment?) : this(listener, context = null) {
        this.fragment = fragment
    }

    /**
     * Activity的onDestroy()中调用，端口链接防止内存泄漏
     */
    fun detach() {
        listener = null
        model = null
        context = null
        fragment = null
    }
}