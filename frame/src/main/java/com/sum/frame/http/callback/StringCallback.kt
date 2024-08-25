package com.sum.frame.http.callback

import com.sum.frame.entity.HttpException
import okhttp3.Response

/**
 * @author  LiuJiang
 * Desc:    StringCallback
 */
abstract class StringCallback : OriginCallback<String> {

    override fun convert(response: Response?) {
        if (null != response && response.isSuccessful) {
            try {
                if (null != response.body) {
                    val result = response.body!!.string()
                    response.body!!.close()
                    onSuccess(result)
                } else {
                    onFail(HttpException(-1, "response body is null"))
                }
            } catch (e: Exception) {
                onFail(HttpException(-1, e.message))
            }
        } else {
            onFail(
                if (null != response)
                    HttpException(response.code, response.message)
                else
                    HttpException(-1, "response is null")
            )
        }
    }

}