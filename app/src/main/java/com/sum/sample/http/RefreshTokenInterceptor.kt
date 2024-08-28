package com.sum.sample.http

import android.content.Intent
import androidx.fragment.app.Fragment
import com.sum.frame.SFrame
import com.sum.frame.entity.HttpException
import com.sum.frame.http.HttpUtils
import com.sum.frame.mvp.BaseActivity
import com.sum.frame.mvp.BaseFragment
import com.sum.frame.utils.SpUtils
import com.sum.frame.utils.print
import com.sum.sample.base.Constant
import com.sum.sample.page.activity.LoginActivity
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author  LiuJiang
 * created  at: 2024/8/27 14:45
 * Desc:    Token刷新拦截器
 */
class RefreshTokenInterceptor : Interceptor {

    private val lock = Any()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        //Token过期
        if (response.code == 401) {
            //加锁
            synchronized(lock) {
                // 多个401同时发生时，若Header中的Token与缓存的Token相同，证明缓存中的Token没有变化，没有换过Token。
                if (SpUtils.getString(Constant.ACCESS_TOKEN, null) == request.header("accessToken")) {
                    //模拟刷新Token
                    try {
                        val map = mutableMapOf("username" to "user_01", "password" to "123456")
                        val result: Response? = HttpUtils.instance
                            .url("/login")
                            .body(map)
                            .postSync()
                        if (result?.code == 200) {
                            val token = "f89ssd9f8sdf98sdfsd9fs"
                            //保存Token
                            SpUtils.putString(Constant.ACCESS_TOKEN, token)
                        } else {
                            refreshTokenFail(request)
                        }
                    } catch (e: Exception) {
                        if (e !is HttpException) {
                            refreshTokenFail(request)
                        } else {
                            throw e
                        }
                    }
                }
            }
            // 换Token后，若Header中的Token与缓存的Token不同。则换Token成功，使用新的Token继续完成当前网络请求。
            if (SpUtils.getString(Constant.ACCESS_TOKEN, null) != request.header("accessToken")) {
                val newRequest = request.newBuilder()
                    .header("accessToken", SpUtils.getString(Constant.ACCESS_TOKEN, null) ?: "")
                    .build()
                return chain.proceed(newRequest)
            } else {
                refreshTokenFail(request)
            }

        }
        return response
    }

    /**
     * Token刷新失败
     */
    private fun refreshTokenFail(request: Request) {
        when (val tag = request.tag()) {
            is BaseActivity<*> -> {
                tag.startActivity(Intent(tag, LoginActivity::class.java))
            }

            is BaseFragment<*> -> {
                tag.startActivity(Intent(tag.context, LoginActivity::class.java))
            }
            else -> {
                val intent = Intent(SFrame.context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                SFrame.context.startActivity(intent)
            }
        }
        //Token刷新失败，抛异常给OriginCallback的onFail。
        throw HttpException(-401, "")
    }
}