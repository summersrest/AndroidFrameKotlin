package com.sum.frame

import android.app.Application
import android.content.Context

/**
 * @author  LiuJiang
 * Desc:
 */
class SFrame {
    companion object {
        private var app: Application? = null

        @JvmStatic
        val context: Context
            get() {
                if (null != app) {
                    return app!!.applicationContext
                }
                throw Exception("请在Application中调用Base.int(this)进行初始化")
            }

        @JvmStatic
        var isShowLog: Boolean = true

        @JvmStatic
        var logTag: String? = null

        @JvmStatic
        fun init(app: Application, isShowLog: Boolean = true, tag: String? = null) {
            this.isShowLog = isShowLog
            this.logTag = tag
            this.app = app
        }
    }
}