package com.sum.framekt.application.pages.user_list.activity

import android.os.Bundle
import com.sum.framekt.application.pages.user_list.presenter.UserListPresenter
import com.sum.framekt.application.pages.user_list.view.UserListView
import com.sum.framekt.base.activity.BaseMvpActivity
import com.sum.framekt.databinding.ActivityUserListBinding

/**
 * @author  LiuJiang
 * created  at: 2023/02/01 15:10
 * Desc:    用户列表
 */
class UserListActivity : BaseMvpActivity<ActivityUserListBinding, UserListPresenter>(), UserListView {
    override fun createPresenter() = UserListPresenter(this, this)

    override fun getBinding() = ActivityUserListBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {

    }

}