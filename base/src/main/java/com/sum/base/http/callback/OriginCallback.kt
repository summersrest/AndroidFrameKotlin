package com.sum.base.http.callback

import com.sum.base.entity.HttpException
import okhttp3.Response

/**
 * @author  LiuJiang
 * Desc:    json解析回调
 */
interface OriginCallback<T> {
    fun convert(response: Response?)

    fun onSuccess(entity: T?)

    fun onFail(e: HttpException)
}