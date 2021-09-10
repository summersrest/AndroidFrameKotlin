package com.pactera.frame.application

import android.content.Context
import com.pactera.frame.base.http.JsonCallback
import com.pactera.frame.base.mvp.BasePresenter

/**
 * @author liujiang
 * created at: 2021/9/10 16:21
 * Desc:
 */
class MainPresenter(mainView: MainView, context: Context) : BasePresenter<MainModel, MainView>(mainView, context) {

    override fun createModel(): MainModel {
        return MainModel(context)
    }

    fun getRequest() {
        model.getRequest(object : JsonCallback<String>() {
            override fun onSuccess(t: String) {

            }

            override fun onError(msg: String) {
            }

        })
    }
}