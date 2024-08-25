package com.sum.androidframekt.page.view

import com.sum.base.mvp.BaseView
import com.sum.androidframekt.entity.UserInfoEntity

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:22
 * Desc:
 */
interface LoginView: BaseView {
    fun loginSuccess(userInfoEntity: UserInfoEntity)
}