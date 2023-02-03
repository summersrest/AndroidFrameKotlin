package com.sum.framekt.base.http

import com.sum.framekt.base.utils.Config
import java.io.File

/**
 * @author  LiuJiang
 * Desc:    网络请求
 */
class HttpUtils {
    companion object{
        val instance: HttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HttpUtils() }
    }
    private var parameter: MutableMap<String, Any?> = HashMap()
    private var body: Any? = null
    private var tag: Any? = null
    private var url: String = ""

//    fun setParameter(key: String, value: String?): HttpUtils {
//        parameter[key] = value
//        return this
//    }
//
//    fun setParameter(key: String, value: Float?): HttpUtils {
//        parameter[key] = value
//        return this
//    }
//
//    fun setParameter(key: String, value: Double?): HttpUtils {
//        parameter[key] = value
//        return this
//    }
//
//    fun setParameter(key: String, value: Long?): HttpUtils {
//        parameter[key] = value
//        return this
//    }
//
//    fun setParameter(key: String, value: Boolean?): HttpUtils {
//        parameter[key] = value
//        return this
//    }
//
//    fun setParameter(key: String, value: File?): HttpUtils {
//        parameter[key] = value
//        return this
//    }

    fun setParameter(key: String, value: Any?): HttpUtils {
        parameter[key] = value
        return this
    }

    fun setBody(body: Any?): HttpUtils {
        this.body = body
        return this
    }

    fun tag(tag: Any?): HttpUtils {
        this.tag = tag
        return this
    }

    fun url(url: String?): HttpUtils {
        this.url = Config.BASE_SERVICE + url
        return this
    }

    private fun clearParameter() {
        parameter.clear()
        body = null
        tag = null
        url = ""
    }

    private fun setCommonParameter() {

    }

    fun <T> getRequest(jsonCallback: JsonCallback<T>) {
        setCommonParameter()
        OkGoRequest().getRequest(tag, url, parameter, jsonCallback)
        clearParameter()
    }

    fun <T> postRequest(jsonCallback: JsonCallback<T>) {
        setCommonParameter()
        OkGoRequest().postRequest(tag, url, parameter, body, jsonCallback)
        clearParameter()
    }


    fun cancelAll() {

    }

    fun cancelByTag(tag: Any?) {

    }
}