package com.sum.framekt.application.pages

import android.content.Context
import com.sum.framekt.application.pojo.UserBean
import com.sum.framekt.base.http.JsonCallback
import com.sum.framekt.base.mvp.BaseModel
import com.sum.framekt.base.utils.Config

/**
 * @author  liujiang
 * created  at: 2023/1/20 16:58
 * Desc:
 */
class MainModel(context: Context?) : BaseModel(context) {
    fun login(userName: String, password: String, jsonCallback: JsonCallback<UserBean>) {
        httpUtils.tag(context)
            .url(Config.LOGIN)
            .setBody(mapOf("userName" to userName, "password" to password))
            .postRequest(jsonCallback)
    }
}