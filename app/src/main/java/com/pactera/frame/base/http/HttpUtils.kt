package com.pactera.frame.base.http

import com.lzy.okgo.OkGo
import java.io.File
import java.util.*

/**
 * @author liujiang
 * created at: 2021/9/9 14:39
 * Desc:
 */
class HttpUtils private constructor() {
    companion object {
        val instance: HttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpUtils()
        }
    }

    //parameter参数
    private val parameter: MutableMap<String, Any> = HashMap()

    //body参数
    private var body: String? = null

    fun setParameter(key: String, value: String) {
        parameter[key] = value
    }

    fun setParameter(key: String, value: Int) {
        parameter[key] = value
    }

    fun setParameter(key: String, value: Long) {
        parameter[key] = value
    }

    fun setParameter(key: String, value: Float) {
        parameter[key] = value
    }

    fun setParameter(key: String, value: Boolean) {
        parameter[key] = value
    }

    fun setParameter(key: String, value: File) {
        parameter[key] = value
    }

    fun setBody(body: String) {
        this.body = body
    }

    /**
     * 设置公共参数
     */
    private fun setCommonParameter() {}

    private fun clearParameter() {
        parameter.clear()
        body = null
    }

    /**
     * Get请求
     */
    fun <T> getRequest(url: String, jsonCallback: JsonCallback<T>, tag: Any? = null) {
        setCommonParameter()
        Okgo().getRequest(tag, url, parameter, jsonCallback)
        clearParameter()
    }

    /**
     * Post请求
     */
    fun <T> postRequest(url: String, jsonCallback: JsonCallback<T>, tag: Any? = null) {
        setCommonParameter()
        Okgo().postRequest(tag, url, parameter, body, jsonCallback)
        clearParameter()
    }

    /**
     * 取消所有网络请求
     */
    fun cancelAll() {
        //取消所有请求
        OkGo.getInstance().cancelAll()
    }

    /**
     * 取消某一个网络请求
     */
    fun cancelTag(tag: String) {
        //取消所有请求
        OkGo.getInstance().cancelTag(tag)
    }
}