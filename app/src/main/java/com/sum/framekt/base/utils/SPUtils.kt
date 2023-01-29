package com.sum.framekt.base.utils

import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.sum.framekt.application.App
import com.sum.framekt.application.pojo.UserBean
import org.apache.commons.codec.binary.Base64
import java.io.*

/**
 * @author  LiuJiang
 * Desc:    SharedPreference
 */
class SPUtils {
    companion object {
        private const val FILE_NAME: String = "share_data"
        private const val MODE_PRIVATE: Int = ContextWrapper.MODE_PRIVATE

        /**
         * 设置boolean类型的全局参数
         */
        fun setBoolean(key: String, value: Boolean) {
            val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putBoolean(key, value).apply()
        }

        /**
         * 根据key值获取boolean参数的value
         */
        fun getBoolean(key: String, def: Boolean): Boolean {
            val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getBoolean(key, def)
        }

        /**
         * 设置int类型的全局参数
         */
        fun setInt(key: String, value: Int) {
            val sp: SharedPreferences = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putInt(key, value).apply()
        }

        /**
         * 根据key值获取int参数的value
         */
        fun getInt(key: String, def: Int): Int {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getInt(key, def)
        }

        /**
         * 设置String类型的全局参数
         */
        fun setString(key: String, value: String) {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putString(key, value).apply()
        }

        /**
         * 根据key值获取String参数的value
         */
        fun getString(key: String, def: String?): String? {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getString(key, def)
        }

        /**
         * 设置float类型的全局参数
         */
        fun setFloat(key: String, value: Float) {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putFloat(key, value).apply()
        }

        /**
         * 根据key值获取float参数的value
         */
        fun getFloat(key: String, def: Float): Float {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getFloat(key, def)
        }

        /**
         * 设置long类型的全局参数
         */
        fun setLong(key: String, value: Long) {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            sp.edit().putLong(key, value).apply()
        }

        /**
         * 根据key值获取long参数的value
         */
        fun getLong(key: String, def: Long): Long {
            val sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            return sp.getLong(key, def)
        }

//        /**
//         * 存储个人信息
//         */
//        fun setUserInfo(userBean: UserBean) {
//            setObject<Any>(userBean, "USER", "MEMEBER")
//        }
//
//        /**
//         * 清除个人信息
//         */
//        fun clearUserInfo() {
//            val editor = App.instance().getSharedPreferences("USER", 0).edit()
//            editor.clear().apply()
//        }
//
//        /**
//         * 读取个人信息
//         */
//        fun getUserInfo(activity: AppCompatActivity): UserBean? {
//            val bean: UserBean? = getObject("USER", "MEMEBER")
//            if (null == bean) {
//                //用户信息为空，重新登录
//                val intent = Intent(activity, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                activity.startActivity(intent)
//                activity.finish()
//            }
//            return bean
//        }


        private fun <T> setObject(obj: T, uKey: String, key: String) {
            // 创建字节输出流
            val baos = ByteArrayOutputStream()
            try {
                // 创建对象输出流，并封装字节流
                val oos = ObjectOutputStream(baos)
                // 将对象写入字节流
                oos.writeObject(obj)
                // 将字节流编码成base64的字符窜
                val value = String(Base64.encodeBase64(baos.toByteArray()))
                val editor = App.instance().getSharedPreferences(uKey, 0).edit()
                editor.putString(key, value)
                editor.apply()
            } catch (_: IOException) {
            }
        }

        private fun <T> getObject(Ukey: String, key: String): T? {
            var obj: T? = null
            val productBase64 = App.instance().getSharedPreferences(Ukey, 0).getString(key, null) ?: return null
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