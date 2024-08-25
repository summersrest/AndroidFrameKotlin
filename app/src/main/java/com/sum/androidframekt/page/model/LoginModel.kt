package com.sum.androidframekt.page.model

import com.alibaba.fastjson2.JSON
import com.sum.base.mvp.BaseModel
import com.sum.androidframekt.entity.UserInfoEntity
import com.sum.androidframekt.http.JsonCallback

/**
 * @author  LiuJiang
 * created  at: 2024/8/24 17:23
 * Desc:
 */
class LoginModel(tag: Any?) : BaseModel(tag) {
    fun login(userName: String, password: String, jsonCallback: JsonCallback<UserInfoEntity>) {
        val map = mutableMapOf("username" to userName, "password" to password)
        httpUtils
            .tag(tag)
            .url("/login")
            .body(map)
            .post(jsonCallback)
    }
}