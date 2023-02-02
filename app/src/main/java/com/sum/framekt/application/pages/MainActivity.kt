package com.sum.framekt.application.pages

import android.os.Bundle
import android.view.View

import com.sum.framekt.application.pages.user_list.activity.UserListActivity
import com.sum.framekt.application.pojo.User
import com.sum.framekt.base.activity.BaseMvpActivity
import com.sum.framekt.base.utils.ActivityUtils
import com.sum.framekt.base.utils.isEmpty
import com.sum.framekt.databinding.ActivityMainBinding

class MainActivity : BaseMvpActivity<ActivityMainBinding, MainPresenter>(), MainView {
    override fun createPresenter(): MainPresenter = MainPresenter(this, this)

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.btnLogin.setOnClickListener(this@MainActivity)
    }

    override fun onClickEvent(v: View?) {
        when(v) {
            viewBinding.btnLogin-> {
                val userName = viewBinding.etUserName.isEmpty("请输入用户名") ?: return
                val password = viewBinding.etPassword.isEmpty("请输入密码") ?: return
                presenter?.login(userName, password)

            }
        }
    }

    override fun loginSuccess() {
        ActivityUtils.startActivityAndFinish(this@MainActivity, UserListActivity::class.java)
    }
}