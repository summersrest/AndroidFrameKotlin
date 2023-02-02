package com.sum.framekt.application.pages.user_list.presenter

import android.content.Context
import com.sum.framekt.application.pages.user_list.model.UserListModel
import com.sum.framekt.application.pages.user_list.view.UserListView
import com.sum.framekt.base.mvp.BasePresenter

/**
 * @author  LiuJiang
 * created  at: 2023/02/01 15:10
 * Desc:    用户列表
 */
class UserListPresenter(listener: UserListView, context: Context?) : BasePresenter<UserListModel, UserListView>(listener, context) {
    override fun createModel() = UserListModel(context)

}