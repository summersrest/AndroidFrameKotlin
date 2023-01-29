package com.sum.framekt.application.pages

import android.content.Context
import com.sum.framekt.base.mvp.BasePresenter

/**
 * @author liujiang
 * created at: 2023/1/21 17:06
 * Desc:
 */
class MainPresenter(listener: MainView, context: Context) : BasePresenter<MainModel, MainView>(listener, context) {
    override fun createModel(): MainModel = MainModel(context)


}