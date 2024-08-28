package com.sum.frame.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.sum.frame.SFrame.Companion.context
import org.apache.commons.codec.binary.Base64
import java.io.*


/**
 * @author  LiuJiang
 * Desc:    Sp存储工具类
 */
class SpUtils private constructor() {
    companion object {
        private const val FILE_NAME = "share_data"

        /**
         * 设置boolean类型的全局参数
         */
        @JvmStatic
        fun putBoolean(key: String, value: Boolean) {
            val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putBoolean(key, value).apply()
        }

        /**
         * 根据key值获取boolean参数的value
         */
        @JvmStatic
        fun getBoolean(key: String, def: Boolean): Boolean {
            val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getBoolean(key, def)
        }

        /**
         * 设置int类型的全局参数
         */
        @JvmStatic
        fun putInt(key: String, value: Int) {
            val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putInt(key, value).apply()
        }

        /**
         * 根据key值获取int参数的value
         */
        @JvmStatic
        fun getInt(key: String, def: Int): Int {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getInt(key, def)
        }

        /**
         * 设置String类型的全局参数
         */
        @JvmStatic
        fun putString(key: String, value: String) {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putString(key, value).apply()
        }

        /**
         * 根据key值获取String参数的value
         */
        @JvmStatic
        fun getString(key: String, def: String?): String? {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getString(key, def)
        }

        /**
         * 设置float类型的全局参数
         */
        @JvmStatic
        fun putFloat(key: String, value: Float) {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putFloat(key, value).apply()
        }

        /**
         * 根据key值获取float参数的value
         */
        @JvmStatic
        fun getFloat(key: String, def: Float): Float {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getFloat(key, def)
        }

        /**
         * 设置long类型的全局参数
         */
        @JvmStatic
        fun putLong(key: String, value: Long) {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putLong(key, value).apply()
        }

        /**
         * 根据key值获取long参数的value
         */
        @JvmStatic
        fun getLong(key: String, def: Long): Long {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getLong(key, def)
        }

        /**
         * 根据Key值移除
         */
        fun remove(key: String) {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().remove(key).apply()
        }

        /**
         * 清空
         */
        fun clear() {
            val sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().clear().apply()
        }

        /**
         *存储复杂对象
         */
        @JvmStatic
        fun <T> putObject(obj: T, key: String) {
            // 创建字节输出流
            val os = ByteArrayOutputStream()
            try {
                // 创建对象输出流，并封装字节流
                val oos = ObjectOutputStream(os)
                // 将对象写入字节流
                oos.writeObject(obj)
                // 将字节流编码成base64的字符窜
                val value = String(Base64.encodeBase64(os.toByteArray()))
                val editor = context.getSharedPreferences(FILE_NAME, 0).edit()
                editor.putString(key, value)
                editor.apply()
            } catch (_: IOException) {
            }
        }

        @JvmStatic
        fun <T> getObject(key: String): T? {
            var obj: T? = null
            val productBase64 = context.getSharedPreferences(FILE_NAME, 0).getString(key, null) ?: return null
            // 读取字节
            val base64 = Base64.decodeBase64(productBase64.toByteArray())
            // 封装到字节流
            val bais = ByteArrayInputStream(base64)
            try {
                // 再次封装
                val bis = ObjectInputStream(bais)
                try {
                    // 读取对象
                    val o = bis.readObject()
                    obj = o as T
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
            } catch (e: StreamCorruptedException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return obj
        }
    }
}