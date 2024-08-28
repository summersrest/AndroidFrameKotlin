package com.sum.sample

import android.app.Application
import com.sum.frame.SFrame
import com.sum.frame.http.HttpUtils
import com.sum.sample.http.RefreshTokenInterceptor

/**
 * @author  LiuJiang
 * Desc:    Application
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initBase()
        initHttp()
    }

    /**
     * 初始化Frame框架
     */
    private fun initBase() {
        SFrame.init(
            app = this,
            //是否打印日志
            isShowLog = BuildConfig.DEBUG,
            //日志tag
            tag = "logTag"
        )
    }

    /**
     * 初始化Http配置
     */
    private fun initHttp() {
        HttpUtils.builder()
            //网络请求BaseUrl
            .baseUrl(BuildConfig.baseUrl)
            //是否打印Http请求日志，若SFrame设置不打印，则此设置无效。(不设置日志打印拦截器时生效)
            .showLog(true)
            //Http日志tag，若未设置，则使用SFrame的tag(不设置日志打印拦截器时生效)
            .tag("okHttp")
            //Token刷新拦截器
            .interceptor(RefreshTokenInterceptor())
            //自定义自定义日志打印拦截器
            //.printerInterceptor(HttpLoggingInterceptor())
            .build()
    }


}