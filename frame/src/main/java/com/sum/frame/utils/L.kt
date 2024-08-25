package com.sum.frame.utils

import android.util.Log
import com.sum.frame.SFrame

/**
 * @author  LiuJiang
 * Desc:    日志打印
 */
class L {
    companion object {
        private const val tag = "Logger"

        @JvmStatic
        fun showI(message: String?) {
            if (SFrame.isShowLog) {
                Log.i(SFrame.logTag ?: tag, message ?: "")
            }
        }

        @JvmStatic
        fun showI(tag: String?, message: String?) {
            if (SFrame.isShowLog) {
                Log.i(tag ?: SFrame.logTag ?: this.tag, message ?: "")
            }
        }

        @JvmStatic
        fun showD(message: String?) {
            if (SFrame.isShowLog) {
                Log.d(SFrame.logTag ?: tag, message ?: "")
            }
        }

        @JvmStatic
        fun showD(tag: String?, message: String?) {
            if (SFrame.isShowLog) {
                Log.d(tag ?: SFrame.logTag ?: this.tag, message ?: "")
            }
        }

        @JvmStatic
        fun showW(message: String?) {
            if (SFrame.isShowLog) {
                Log.w(SFrame.logTag ?: tag, message ?: "")
            }
        }

        @JvmStatic
        fun showW(tag: String?, message: String?) {
            if (SFrame.isShowLog) {
                Log.w(tag ?: SFrame.logTag ?: this.tag, message ?: "")
            }
        }

        @JvmStatic
        fun showV(message: String?) {
            if (SFrame.isShowLog) {
                Log.v(SFrame.logTag ?: tag, message ?: "")
            }
        }

        @JvmStatic
        fun showV(tag: String?, message: String?) {
            if (SFrame.isShowLog) {
                Log.v(tag ?: SFrame.logTag ?: this.tag, message ?: "")
            }
        }

        @JvmStatic
        fun showE(message: String?) {
            if (SFrame.isShowLog) {
                Log.e(SFrame.logTag ?: tag, message ?: "")
            }
        }

        @JvmStatic
        fun showE(tag: String, message: String?) {
            if (SFrame.isShowLog) {
                Log.e(tag, message ?: "")
            }
        }
    }
}