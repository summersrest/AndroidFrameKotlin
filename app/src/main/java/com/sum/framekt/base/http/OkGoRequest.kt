package com.sum.framekt.base.http

import com.alibaba.fastjson2.toJSONString
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import java.io.File

/**
 * @author  LiuJiang
 * Desc:    网络请求
 */
class OkGoRequest {
    /**
     * Get请求
     */
    fun <T> getRequest(tag: Any?, url: String, parameter: MutableMap<String, Any?>, jsonCallback: AbsCallback<T>) {
        val get: GetRequest<T> = OkGo.get(url)
        get.headers("X-Access-Token", "")
        tag?.let { get.tag(tag) }
        parameter.forEach { (key, value) ->
            when (value) {
                is String -> {
                    get.params(key, value)
                }
                is Int -> {
                    get.params(key, value)
                }
                is Float -> {
                    get.params(key, value)
                }
                is Double -> {
                    get.params(key, value)
                }
                is Long -> {
                    get.params(key, value)
                }
                is Boolean -> {
                    get.params(key, value)
                }
            }
        }
        get.execute(jsonCallback)
    }

    /**
     * post请求
     */
    fun <T> postRequest(tag: Any?, url: String, parameter: MutableMap<String, Any?>, body: Any?, jsonCallback: AbsCallback<T>) {
        val post: PostRequest<T> = OkGo.post(url)
        post.headers("X-Access-Token", "")
        tag?.let { post.tag(tag) }
        parameter.forEach { (key, value) ->
            when (value) {
                is String -> {
                    post.params(key, value)
                }
                is Int -> {
                    post.params(key, value)
                }
                is Float -> {
                    post.params(key, value)
                }
                is Double -> {
                    post.params(key, value)
                }
                is Long -> {
                    post.params(key, value)
                }
                is Boolean -> {
                    post.params(key, value)
                }
                is File -> {
                    post.params(key, value)
                }
            }
        }
        body?.let { post.upJson(it.toJSONString()) }
//        body?.let { post.upJson(JSON.toJSONString(it)) }
        post.execute(jsonCallback)
    }
}



