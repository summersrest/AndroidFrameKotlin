package com.sum.framekt.base.utils

import android.app.Activity
import android.content.Intent
import java.io.Serializable

/**
 * @author  LiuJiang
 * Desc:    Activity工具类
 */
class ActivityUtils {
    companion object {
        /**
         * 启动Activity
         */
        fun startActivity(activity: Activity, cls: Class<*>?) {
            val intent = Intent(activity, cls)
            activity.startActivity(intent)
            startActivityAnim(activity)
        }

        /**
         * 启动一个Activity并关闭当前Activity
         */
        fun startActivityAndFinish(activity: Activity, cls: Class<*>) {
            val intent = Intent(activity, cls)
            activity.startActivity(intent)
            activity.finish()
            startActivityAnim(activity)
        }

        /**
         * 启动activity并传递参数
         */
        fun startActivity(activity: Activity, cls: Class<*>, vararg args: Any) {
            val intent = Intent(activity, cls)
            args.forEachIndexed { index, any ->
                when (any) {
                    is Boolean -> intent.putExtra("data${(index + 1)}", any)
                    is Byte -> intent.putExtra("data${(index + 1)}", any)
                    is Char -> intent.putExtra("data${(index + 1)}", any)
                    is Short -> intent.putExtra("data${(index + 1)}", any)
                    is Int -> intent.putExtra("data${(index + 1)}", any)
                    is Long -> intent.putExtra("data${(index + 1)}", any)
                    is Double -> intent.putExtra("data${(index + 1)}", any)
                    is Float -> intent.putExtra("data${(index + 1)}", any)
                    is String -> intent.putExtra("data${(index + 1)}", any)
                    is Serializable -> intent.putExtra("data${(index + 1)}", any)
                }
            }
            activity.startActivity(intent)
            startActivityAnim(activity)
        }

        /**
         * 启动activity传递参数并且关闭当前activity
         */
        fun startActivityAndFinish(activity: Activity, cls: Class<*>, vararg args: Any) {
            val intent = Intent(activity, cls)
            args.forEachIndexed { index, any ->
                when (any) {
                    is Boolean -> intent.putExtra("data${(index + 1)}", any)
                    is Byte -> intent.putExtra("data${(index + 1)}", any)
                    is Char -> intent.putExtra("data${(index + 1)}", any)
                    is Short -> intent.putExtra("data${(index + 1)}", any)
                    is Int -> intent.putExtra("data${(index + 1)}", any)
                    is Long -> intent.putExtra("data${(index + 1)}", any)
                    is Double -> intent.putExtra("data${(index + 1)}", any)
                    is Float -> intent.putExtra("data${(index + 1)}", any)
                    is String -> intent.putExtra("data${(index + 1)}", any)
                    is Serializable -> intent.putExtra("data${(index + 1)}", any)
                }
            }
            activity.startActivity(intent)
            activity.finish()
            startActivityAnim(activity)
        }

        /**
         * 启动一个Activity并清空所有Activity
         */
        fun startActivityAndClear(activity: Activity, cls: Class<*>) {
            val intent = Intent(activity, cls)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(intent)
            startActivityAnim(activity)
        }

        private fun startActivityAnim(activity: Activity) {
//        activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }

}