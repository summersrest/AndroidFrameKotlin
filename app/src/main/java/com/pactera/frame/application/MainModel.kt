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
    fun getRequest(jsonCallback: JsonCallback<String>) {
        val url = "http://10.113.6.145:8090/production_ms_scgl_war_exploded/employee/get_data"
        HttpUtils.instance.getRequest(url, jsonCallback, context)
    }
}