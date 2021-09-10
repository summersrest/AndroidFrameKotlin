package com.pactera.frame.base.mvp

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * @author liujiang
 * Desc:
 */
abstract class BasePresenter<M : BaseModel, V : BaseView> (protected var listener: V, protected var context: Context){
    protected var model: M

    abstract fun createModel(): M

    init {
        model = createModel()
    }
}