package com.sum.sample.http

import com.alibaba.fastjson2.*
import com.sum.frame.entity.HttpException
import com.sum.frame.http.callback.OriginCallback
import com.sum.frame.utils.print
import com.sum.sample.entity.BaseEntity
import okhttp3.Response
import java.lang.reflect.ParameterizedType

/**
 * @author  LiuJiang
 * Desc:    Json解包器
 */
abstract class JsonCallback<T> : OriginCallback<T> {

    override fun convert(response: Response?) {
        if (null != response && response.isSuccessful) {
            try {
                response.body?.let { body ->
                    val content = body.string()
                    body.close()
                    val entity: BaseEntity<T>? = content.to()
                    entity?.let {
                        if (entity.error == 0) {
                            val genType = javaClass.genericSuperclass
                            val params = (genType as ParameterizedType).actualTypeArguments
                            val type = params[0]
                            if (type === String::class.java || type === Int::class.java || type === Boolean::class.java ||
                                type === Double::class.java || type === Float::class.java || type === Long::class.java) {
                                onSuccess(entity.data)
                            } else if (null == entity.data) {
                                onSuccess(entity.data)
                            } else {
                                try {
                                    val result: T? = JSON.parseObject(entity.data.toString(), type)
                                    onSuccess(result)
                                } catch (e: Exception) {
                                    onFail(HttpException(-1, "数据解析错误"))
                                }
                            }
                        } else {
                            onFail(HttpException(entity.error, entity.message))
                        }
                    } ?: onFail(HttpException(-1, "数据解析错误"))
                } ?: onFail(HttpException(-1, "返回body体为空"))
            } catch (e: Exception) {
                onFail(HttpException(-1, e.message))
            }
        } else {
            onFail(
                if (null != response)
                    HttpException(response.code, response.message)
                else
                    HttpException(-1, "返回为空")
            )
        }
    }

}