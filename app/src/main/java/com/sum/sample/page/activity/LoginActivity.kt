package com.sum.sample.page.activity

import android.os.Bundle
import android.view.View
import com.sum.frame.http.HttpUtils
import com.sum.frame.mvp.BaseMvpActivity
import com.sum.frame.utils.SpUtils
import com.sum.sample.databinding.ActivityLoginBinding
import com.sum.sample.entity.UserInfoEntity
import com.sum.sample.page.presenter.LoginPresenter
import com.sum.sample.page.view.LoginView
import com.sum.frame.utils.ToastUtils
import com.sum.sample.base.Constant

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:31
 * Desc:    登录
 */
class LoginActivity : BaseMvpActivity<ActivityLoginBinding, LoginPresenter>(), LoginView {
    override fun createPresenter(): LoginPresenter = LoginPresenter(this, this)

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.btnLogin.setOnClickListener{
            presenter?.getList()
        }
        viewBinding.btnRegister.setOnClickListener {
            SpUtils.remove(Constant.ACCESS_TOKEN)
        }
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