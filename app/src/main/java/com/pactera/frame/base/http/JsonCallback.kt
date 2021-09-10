package com.pactera.frame.base.http

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.lzy.okgo.callback.AbsCallback
import com.pactera.frame.base.pojo.BasePojo
import okhttp3.Response
import java.lang.reflect.ParameterizedType

/**
 * @author liujiang
 * Desc: 返回数据解析
 */
abstract class JsonCallback<T> : AbsCallback<T>() {
    override fun convertResponse(response: Response?): T {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0]
        val content = response?.body()?.string()
        val pojo: BasePojo<T>? = JSON.parseObject(content, object : TypeReference<BasePojo<T>>() {})
        response?.close()
        if (pojo?.code == 200) {
            if (type === String::class.java || type === Int::class.java || type === Boolean::class.java || type === Double::class.java || type === Float::class.java) {
                return pojo.result
            }
            return try {
                JSON.parseObject(pojo.result.toString(), type)
            } catch (e : java.lang.Exception) {
                pojo.result
            }
        } else if (pojo?.code != 200) {
            throw Exception(pojo?.message)
        } else {
            throw Exception("服务的接口错误")
        }
    }

    override fun onSuccess(response: com.lzy.okgo.model.Response<T>) {
        onSuccess(response.body())
    }

    protected abstract fun onSuccess(t: T)

    override fun onError(response: com.lzy.okgo.model.Response<T>) {
        super.onError(response)
        onError(response.exception.message!!)
    }

    protected abstract fun onError(msg: String)
}