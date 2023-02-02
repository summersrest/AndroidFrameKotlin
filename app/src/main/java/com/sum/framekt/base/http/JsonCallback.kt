package com.sum.framekt.base.http

import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.Response
import com.sum.framekt.base.pojo.BasePojo
import com.sum.framekt.base.pojo.ResultPojo
import java.lang.reflect.ParameterizedType
import com.alibaba.fastjson2.*

/**
 * @author  LiuJiang
 * Desc:    返回数据解析
 */
abstract class JsonCallback<T> : AbsCallback<ResultPojo<T>>() {
    abstract fun onSuccess(result: T?, msg: String?)

    abstract fun onError(msg: String?)

    override fun convertResponse(response: okhttp3.Response?): ResultPojo<T>? {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0]
        response?.let {
            val content = it.body()?.string()
//            val basePojo: BasePojo<T>? = JSON.parseObject(content, object : TypeReference<BasePojo<T>>() {})
            val basePojo: BasePojo<T>? = content.to()
            it.close()
            basePojo?.let { pojo ->
                if (pojo.error == 0) {
                    if (type === String::class.java || type === Int::class.java || type === Boolean::class.java || type === Double::class.java || type === Float::class.java) {
                        return ResultPojo(pojo.data, pojo.message)
                    }
                    try {
                        return ResultPojo(JSON.parseObject(pojo.data.toString(), type), pojo.message)
                    } catch (e: java.lang.Exception){
                        return ResultPojo(pojo.data, pojo.message)
                    }
                } else {
                    throw java.lang.IllegalStateException(pojo.message)
                }
            } ?: throw IllegalStateException("服务端接口错误")
        } ?: throw IllegalStateException("服务端接口错误")
    }

    override fun onSuccess(response: Response<ResultPojo<T>>?) {
        onSuccess(response?.body()?.data, response?.body()?.message)
    }

    override fun onError(response: Response<ResultPojo<T>>?) {
        super.onError(response)
        onError(response?.exception?.message)
    }
}
