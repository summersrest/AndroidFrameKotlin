package com.pactera.frame.application

import android.content.Context
import com.pactera.frame.base.http.HttpUtils
import com.pactera.frame.base.http.JsonCallback
import com.pactera.frame.base.mvp.BaseModel

/**
 * @author liujiang
 * created at: 2021/9/10 16:07
 * Desc:
 */
class MainModel(context: Context) : BaseModel(context) {
    fun getRequest(jsonCallback: JsonCallback<MutableList<UserBean>>) {
        val url = "http://192.168.0.104:8090/user/selectList"
        httpUtils.getRequest(url, jsonCallback, context)
    }

    fun getDetail(jsonCallback: JsonCallback<UserBean>) {
        var url = "http://192.168.0.104:8090/user/getById"
        httpUtils.setParameter("id", "1")
        httpUtils.getRequest(url, jsonCallback, context)
    }
}