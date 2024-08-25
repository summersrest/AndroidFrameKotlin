package com.sum.androidframekt.page.activity

import android.os.Bundle
import android.view.View
import com.sum.base.mvp.BaseMvpActivity
import com.sum.androidframekt.databinding.ActivityLoginBinding
import com.sum.androidframekt.entity.UserInfoEntity
import com.sum.androidframekt.page.presenter.LoginPresenter
import com.sum.androidframekt.page.view.LoginView
import com.sum.base.utils.ToastUtils

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:31
 * Desc:    登录
 */
class LoginActivity : BaseMvpActivity<ActivityLoginBinding, LoginPresenter>(), LoginView {
    override fun createPresenter(): LoginPresenter = LoginPresenter(this, this)

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.btnLogin.setOnClickListener(this)
    }

    override fun onClickEvent(v: View) {
        super.onClickEvent(v)
        when (v) {
            viewBinding.btnLogin -> presenter?.login(viewBinding.etUser, viewBinding.etPwd)
        }
    }

    override fun loginSuccess(userInfoEntity: UserInfoEntity) {
        ToastUtils.show("登录成功")
    }

}