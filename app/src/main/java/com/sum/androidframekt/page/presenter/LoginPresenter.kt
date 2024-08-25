package com.sum.androidframekt.page.presenter

import android.widget.EditText
import com.sum.base.entity.HttpException
import com.sum.base.mvp.BasePresenter
import com.sum.base.utils.getContent
import com.sum.base.utils.isEmpty
import com.sum.androidframekt.entity.UserInfoEntity
import com.sum.androidframekt.http.JsonCallback
import com.sum.androidframekt.page.model.LoginModel
import com.sum.androidframekt.page.view.LoginView

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:24
 * Desc:
 */
class LoginPresenter(tag: Any?, view: LoginView) : BasePresenter<LoginModel, LoginView>(tag, view) {

    override fun createMode(): LoginModel = LoginModel(tag)

    fun login(etUser: EditText, etPwd: EditText) {
        etUser.isEmpty("请输入用户名") ?: return
        etPwd.isEmpty("请输入密码") ?: return
        view?.showProgressDialog("登录中")
        model?.login(etUser.getContent(), etPwd.getContent(), object : JsonCallback<UserInfoEntity>() {
            override fun onSuccess(entity: UserInfoEntity?) {
                view?.hideProgressDialog()
                if (null != entity) {
                    view?.loginSuccess(entity)
                } else {
                    view?.hideProgressDialog("用户信息获取失败")
                }
            }

            override fun onFail(e: HttpException) {
                view?.hideProgressDialog(e.message)
            }

        })
    }
}