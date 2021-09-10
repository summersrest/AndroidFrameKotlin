package com.pactera.frame.base.utils

import android.util.Log

/**
 * @author liujiang
 * created at: 2021/9/9 10:18
 * Desc:
 */
object L {
    private fun L() {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    var isDebug = true // 是否需要打印bug，可以在application的onCreate函数里面初始化

    private const val TAG = "green"

    // 下面四个是默认tag的函数
    fun showI(msg: String?) {
        if (isDebug) Log.i(TAG, msg!!)
    }

    fun showD(msg: String?) {
        if (isDebug) Log.d(TAG, msg!!)
    }

    fun showE(msg: String?) {
        if (isDebug) Log.e(TAG, msg!!)
    }

    fun showV(msg: String?) {
        if (isDebug) Log.v(TAG, msg!!)
    }


    // 下面是传入自定义tag的函数
    fun CusI(tag: String?, msg: String?) {
        if (isDebug) Log.i(tag, msg!!)
    }

    fun CusD(tag: String?, msg: String?) {
        if (isDebug) Log.d(tag, msg!!)
    }

    fun CusE(tag: String?, msg: String?) {
        if (isDebug) Log.e(tag, msg!!)
    }

    fun CusV(tag: String?, msg: String?) {
        if (isDebug) Log.v(tag, msg!!)
    }
}