package com.sum.framekt.base.utils

import android.util.Log

/**
 * @author  liujiang
 * created  at: 2023/1/20 15:38
 * Desc:
 */
class Log {
    companion object {
        private const val isDebug = true
        private const val TAG = "green"

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
        fun CusI(tag: String, msg: String?) {
            if (isDebug) Log.i(tag, msg!!)
        }

        fun CusD(tag: String, msg: String?) {
            if (isDebug) Log.d(tag, msg!!)
        }

        fun CusE(tag: String, msg: String?) {
            if (isDebug) Log.e(tag, msg!!)
        }

        fun CusV(tag: String, msg: String?) {
            if (isDebug) Log.v(tag, msg!!)
        }
    }
}