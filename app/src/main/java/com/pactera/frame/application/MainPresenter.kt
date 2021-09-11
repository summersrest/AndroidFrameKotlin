package com.pactera.frame.application

import android.content.Context
import com.alibaba.fastjson.JSON
import com.pactera.frame.base.http.JsonCallback
import com.pactera.frame.base.mvp.BasePresenter
import com.pactera.frame.base.utils.L

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
        model.getRequest(object : JsonCallback<MutableList<UserBean>>() {
            override fun onSuccess(t: MutableList<UserBean>) {
                for (userBean : UserBean in t) {
                    L.showD(userBean.name)
                }
            }

            override fun onError(msg: String) {
            }

        })
    }

    fun getDetail() {
        model.getDetail(object  : JsonCallback<UserBean>() {
            override fun onSuccess(t: UserBean) {
                L.showD(JSON.toJSONString(t))
            }

            override fun onError(msg: String) {
            }

        })
    }
}