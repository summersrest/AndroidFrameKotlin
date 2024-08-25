package com.sum.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

/**
 * @author  LiuJiang
 * Desc:    时间工具类
 */
class DateUtils private constructor(){
    companion object {
        /**
         * 年月日时分秒
         */
        const val FORMAT_YY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

        /**
         * 年月日时分
         */
        const val FORMAT_YY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"

        /**
         * 年月日
         */
        const val FORMAT_YY_MM_DD = "yyyy-MM-dd"

        /**
         * 月日
         */
        const val FORMAT_MM_DD = "MM-dd"

        /**
         * 月日时分
         */
        const val FORMAT_MM_DD_HH_MM = "MM-dd HH:mm"

        /**
         * 中文年月日时分秒
         */
        const val FORMAT_CN_YY_MM_DD_HH_MM_SS = "yyyy年MM月dd日 HH时mm分ss"

        /**
         * 中文年月日时分
         */
        const val FORMAT_CN_YY_MM_DD_HH_MM = "yyyy年MM月dd日 HH时mm分"

        /**
         * 中文年月日
         */
        const val FORMAT_CN_YY_MM_DD = "yyyy年MM月dd日"

        /**
         * 中文月日
         */
        const val FORMAT_CN_MM_DD = "MM月dd日"

        /**
         * 中文月日时分
         */
        const val FORMAT_CN_MM_DD_HH_MM = "MM月dd日 HHH时mm分"

        /**
         * 获取当前时间戳
         */
        @JvmStatic
        fun getCurrentStamps(): Long {
            return Date().time
        }

        /**
         * 获取当前时间
         */
        @JvmStatic
        fun getCurrent(format: String = "yyyy-MM-dd"): String {
            val sdf = SimpleDateFormat(format, Locale.CHINA)
            return sdf.format(Date())
        }

        /**
         * Date转String
         */
        @JvmStatic
        fun dateToString(date: Date, format: String = "yyyy-MM-dd"): String {
            val sdf = SimpleDateFormat(format, Locale.CHINA)
            return sdf.format(date)
        }

        /**
         * Date转时间戳
         */
        @JvmStatic
        fun dateToStamps(date: Date): Long {
            return date.time
        }

        /**
         * String转Date
         */
        @JvmStatic
        fun stringToDate(source: String, format: String = "yyyy-MM-dd"): Date {
            if (source.isBlank()) return Date()
            var date: Date?
            val sdf = SimpleDateFormat(format, Locale.CHINA)
            try {
                sdf.isLenient = false
                date = sdf.parse(source)
            } catch (e: ParseException) {
                date = Date()
            }
            return date!!
        }

        /**
         * String转时间戳
         */
        @JvmStatic
        fun stringToStamps(source: String, format: String = "yyyy-MM-dd"): Long {
            val date = stringToDate(source, format)
            return date.time
        }

        /**
         * 时间格式字符串格式化
         * [origin] 原字符串格式
         * [target] 目标字符串格式
         * formatString("1980-12-12 21:12:11", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd")
         * result: "1980-12-12"
         */
        @JvmStatic
        fun formatString(source: String, origin: String, target: String): String {
            val date = stringToDate(source, origin)
            return dateToString(date, target)
        }

        /**
         * 时间戳转Date
         */
        @JvmStatic
        fun stampsToDate(stamps: Long): Date {
            val instant = Instant.ofEpochMilli(stamps)
            return Date.from(instant)
        }

        /**
         * 时间戳转字符串
         */
        @JvmStatic
        fun stampsToString(stamps: Long, format: String = "yyyy-MM-dd"): String {
            val sdf = SimpleDateFormat(format, Locale.CHINA)
            return sdf.format(Date(stamps))
        }
    }
}