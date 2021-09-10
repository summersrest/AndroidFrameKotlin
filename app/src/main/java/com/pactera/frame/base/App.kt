package com.pactera.frame.base

import android.app.Application
import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.SPCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * @author liujiang
 * Desc:
 */
class App : Application() {
    companion object {
        var instance: Application? = null

        fun instance() : Context {
            return instance!!
        }
    }



    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    fun initOkHttp() {
        OkGo.getInstance().init(this)
        try {
            val builder = OkHttpClient.Builder()

            //自定义拦截器(定时刷新token)
//            RefreshTokenInterceptor tokenInterceptor = new RefreshTokenInterceptor("OkGo");
//            //log打印级别，决定了log显示的详细程度
//            tokenInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//            //log颜色级别，决定了log在控制台显示的颜色
//            tokenInterceptor.setColorLevel(Level.INFO);
//            builder.addInterceptor(tokenInterceptor);

            //logging拦截器
            val loggingInterceptor = HttpLoggingInterceptor("OkGo")
            //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.INFO)
            builder.addInterceptor(loggingInterceptor)

            //全局的读取超时时间
            builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
            //全局的写入超时时间
            builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
            //全局的连接超时时间
            builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
            //使用sp保持cookie，如果cookie不过期，则一直有效
            builder.cookieJar(CookieJarImpl(SPCookieStore(this)))
            OkGo.getInstance().init(this) //必须调用初始化
                .setOkHttpClient(builder.build()) //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE) //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3 //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
            //                    .addCommonHeaders(headers)                      //全局公共头
//                    .addCommonParams(params);                       //全局公共参数
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}