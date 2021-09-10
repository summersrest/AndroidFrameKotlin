package com.pactera.frame.base.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.pactera.frame.base.App

/**
 * @author liujiang
 * created at: 2021/9/9 10:20
 * Desc:
 */
object SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    const val FILE_NAME = "share_data"

    private const val MODE_PRIVATE = ContextWrapper.MODE_PRIVATE

    /**
     * 设置boolen类型的全局参数
     *
     * @param key
     * @param value
     */
    fun setBoolen(key: String?, value: Boolean) {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putBoolean(key, value).apply()
    }

    /**
     * 根据key值获取boolean参数的value
     *
     * @param key
     * @return
     */
    fun getBoolean(key: String?, def: Boolean): Boolean {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getBoolean(key, def)
    }

    /**
     * 设置int类型的全局参数
     *
     * @param key
     * @param value
     */
    fun setInt(key: String?, value: Int) {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putInt(key, value).apply()
    }

    /**
     * 根据key值获取int参数的value
     *
     * @param key
     * @return
     */
    fun getInt(key: String?, def: Int): Int {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getInt(key, def)
    }

    /**
     * 设置String类型的全局参数
     *
     * @param key
     * @param value
     */
    fun setString(key: String?, value: String?) {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putString(key, value).apply()
    }

    /**
     * 设置String类型的全局参数
     * @param context
     * @param key
     * @param value
     */
    fun setString(context: Context, key: String?, value: String?) {
        val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putString(key, value).apply()
    }


    /**
     * 根据key值获取String参数的value
     *
     * @param key
     * @return
     */
    fun getString(key: String?, def: String?): String? {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getString(key, def)
    }

    /**
     * 根据key值获取String参数的value
     * @param context
     * @param key
     * @param def
     * @return
     */
    fun getString(context: Context, key: String?, def: String?): String? {
        val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getString(key, def)
    }

    /**
     * 设置float类型的全局参数
     *
     * @param key
     * @param value
     */
    fun setFloat(key: String?, value: Float) {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putFloat(key, value).apply()
    }

    /**
     * 根据key值获取float参数的value
     *
     * @param key
     * @return
     */
    fun getFloat(key: String?, def: Float): Float {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getFloat(key, def)
    }

    /**
     * 设置long类型的全局参数
     *
     * @param key
     * @param value
     */
    fun setLong(key: String?, value: Long) {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().putLong(key, value).apply()
    }

    /**
     * 根据key值获取long参数的value
     *
     * @param key
     * @return
     */
    fun getLong(key: String?, def: Long): Long {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        return sp.getLong(key, def)
    }

    /**
     * 清空全部信息
     *
     * @author LiuJiang
     */
    fun clear() {
        val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}