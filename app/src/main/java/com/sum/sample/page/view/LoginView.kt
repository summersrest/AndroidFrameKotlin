package com.sum.sample.page.view

import com.sum.frame.mvp.BaseView
import com.sum.sample.entity.UserInfoEntity

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:22
 * Desc:
 */
interface LoginView: BaseView {
    fun loginSuccess(userInfoEntity: UserInfoEntity)
}