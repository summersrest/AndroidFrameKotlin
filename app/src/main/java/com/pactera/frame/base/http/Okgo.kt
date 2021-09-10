package com.pactera.frame.base.http

import com.lzy.okgo.OkGo
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest

/**
 * @author liujiang
 * Desc: 网络框架
 */
class Okgo {

    /**
     * get请求
     */
    fun <T> getRequest(any: Any?, url: String, parameter: Map<String, Any>, jsonCallback: JsonCallback<T>) {
        val get: GetRequest<T> = OkGo.get(url)
        get.headers("X-Access-Token", "")
        any?.let {
            get.tag(it)
        }
        for (key in parameter.keys) {
            when (val value = parameter[key]) {
                is String -> {
                    get.params(key, value)
                }
                is Int -> {
                    get.params(key, value)
                }
                is Long -> {
                    get.params(key, value)
                }
                is Float -> {
                    get.params(key, value)
                }
                is Double -> {
                    get.params(key, value)
                }
                is Boolean -> {
                    get.params(key, value)
                }
            }
        }
        get.execute(jsonCallback)
    }

    fun <T> postRequest(any: Any?, url: String, parameter: Map<String, Any>, body : String?, jsonCallback: JsonCallback<T>){
        val post: PostRequest<T> = OkGo.post(url)
        any?.let {
            post.tag(it)
        }
        for (key in parameter.keys) {
            when (val value = parameter[key]) {
                is String -> {
                    post.params(key, value)
                }
                is Int -> {
                    post.params(key, value)
                }
                is Long -> {
                    post.params(key, value)
                }
                is Float -> {
                    post.params(key, value)
                }
                is Double -> {
                    post.params(key, value)
                }
                is Boolean -> {
                    post.params(key, value)
                }
            }
        }
        body?.let {
            if (body.isNotEmpty()) {
                post.upJson(it)
            }
        }

        post.execute(jsonCallback)
    }
}