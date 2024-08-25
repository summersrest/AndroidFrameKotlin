package com.sum.frame.http.callback

import com.sum.frame.entity.HttpException
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