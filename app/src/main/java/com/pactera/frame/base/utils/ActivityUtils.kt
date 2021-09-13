package com.pactera.frame.base.utils

import android.app.Activity
import android.content.Intent
import java.io.Serializable

/**
 * @author liujiang
 * Desc: Activity工具类
 */
object ActivityUtils {
    /**
     * 启动Activity
     *
     * @param activity 当前Activity
     * @param cls      要启动的Activity Class
     */
    fun startActivity(activity: Activity, cls: Class<*>?) {
        val intent = Intent(activity, cls)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动一个Activity并关闭当前Activity
     * @param activity
     * @param cls
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?) {
        val intent = Intent(activity, cls)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递int类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递int类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递String类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: String?) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递String类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: String?) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递long类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: Long) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递long类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: Long) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递float类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: Float) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递float类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: Float) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递double类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: Double) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递double类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: Double) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递序列化对象Serializable
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivity(activity: Activity, cls: Class<*>?, data1: Serializable?) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动activity传递序列化对象Serializable并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityAndFinish(activity: Activity, cls: Class<*>?, data1: Serializable?) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        activity.startActivity(intent)
        activity.finish()
        startActivityAnim(activity)
    }

    /**
     * 启动Activity
     * @param activity
     * @param cls
     * @param requestCode
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递int类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, data1: Int, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递long类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, data1: Long, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递float类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, data1: Float, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递double类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, data1: Double, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动activity并传递序列化对象Serializable
     * @param activity
     * @param cls
     * @param data1
     */
    fun startActivityForResult(activity: Activity, cls: Class<*>?, data1: Serializable?, requestCode: Int) {
        val intent = Intent(activity, cls)
        intent.putExtra("data1", data1)
        intent.flags = requestCode
        activity.startActivityForResult(intent, requestCode)
        startActivityAnim(activity)
    }

    /**
     * 启动一个Activity并清空所有Activity
     *
     * @param activity 当前Activity
     * @param cls      要启动的Activity
     */
    fun startActivityAndClear(activity: Activity, cls: Class<*>?) {
        val intent = Intent(activity, cls)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
        startActivityAnim(activity)
    }

    /**
     * 启动Activity动画
     *
     * @param activity
     */
    private fun startActivityAnim(activity: Activity?) {
//        activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
}