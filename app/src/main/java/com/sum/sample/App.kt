package com.sum.sample

import android.app.Application
import com.sum.frame.SFrame
import com.sum.frame.http.HttpUtils.Companion.builder


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

    private fun initBase() {
        SFrame.init(app = this,
            //是否打印日志
            isShowLog = BuildConfig.DEBUG,
            //日志tag
            tag = "logTag")
    }

    private fun initHttp() {
        builder()
            .baseUrl(BuildConfig.baseUrl)
            //是否打印Http请求日志，若SFrame设置不打印，则此值无效
            .showLog(true)
            //Http日志tag，若未设置，则使用SFrame的tag
            .tag("OkHttp")
            .build()
    }


}