package com.sum.framekt.application.pages

import android.content.Context
import com.sum.framekt.application.pojo.UserBean
import com.sum.framekt.base.http.JsonCallback
import com.sum.framekt.base.mvp.BasePresenter

/**
 * @author liujiang
 * created at: 2023/1/21 17:06
 * Desc:
 */
class MainPresenter(listener: MainView, context: Context?) : BasePresenter<MainModel, MainView>(listener, context) {
    override fun createModel(): MainModel = MainModel(context)

    fun login(userName: String, password: String) {
        listener?.showProgressDialog()
        model?.login(userName, password, object : JsonCallback<UserBean>() {
            override fun onSuccess(result: UserBean?, msg: String?) {
                result?.let {
                    listener?.hideProgressDialog()
                    listener?.loginSuccess()
                } ?: listener?.hideProgressDialog("登录失败")
            }

            override fun onError(msg: String?) {
                listener?.hideProgressDialog(msg)
            }

        })
    }
}