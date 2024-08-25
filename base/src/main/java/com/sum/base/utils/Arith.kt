package com.sum.base.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author  LiuJiang
 * Desc:    加减乘除计算
 */
class Arith private constructor(){
    companion object {

        /**
         * 加法
         * Arith.add("1", "2", "3") = "6"
         */
        @JvmStatic
        fun add(vararg values: String): String {
            var result = "0"
            values.forEach { item ->
                val b1 = BigDecimal(result)
                val b2 = BigDecimal(item)
                result = b1.add(b2).toString()
            }
            return result
        }

        /**
         * 加法
         * val list = listOf("1", "2", "3")
         * Arith.add(list) = "6"
         */
        @JvmStatic
        fun add(values: List<String>): String {
            var result = "0"
            values.forEach { item ->
                val b1 = BigDecimal(result)
                val b2 = BigDecimal(item)
                result = b1.add(b2).toString()
            }
            return result
        }

        /**
         * 减法
         */
        @JvmStatic
        fun subtract(value1: String, value2: String): String {
            val b1 = BigDecimal(value1)
            val b2 = BigDecimal(value2)
            return b1.subtract(b2).toString()
        }

        /**
         * 乘法
         */
        @JvmStatic
        fun multiply(vararg values: String): String {
            var result = "1"
            values.forEach { item ->
                val b1 = BigDecimal(result)
                val b2 = BigDecimal(item)
                result = b1.multiply(b2).toString()
            }
            return result
        }

        /**
         * 除法
         * [value1]         被除数
         * [value2]         除数
         * [scale]          精度，保留scale位小数
         * [roundingMode]   精确方式：(假如scale = 1，保留一位小数前提下)
         *                      RoundingMode.DOWN：直接删除多余的小数位，如2.35会变成2.3
         *                      RoundingMode.UP：进位处理，2.35变成2.4
         *                      RoundingMode.HALF_UP：四舍五入，2.35变成2.4
         *                      RoundingMode.HALF_UP：五舍六入，是5则向下舍，2.35变成2.4
         *                      RoundingMode.HALF_EVEN：四舍五入，是5则向偶数邻居靠拢。2.35变成2.4，2.45变成2.4
         *                      RoundingMode.CEILING：向较大的数进位，与UP的区别是负数向较大的数靠拢，而UP的负数向较大数靠拢。如CEILING -2.35变成-2.3，而UP-2.35变成-2.4
         *                      RoundingMode.FLOOR：向较小的数进位，与DOWN的区别是负数向较小的数靠拢，而DOWN的负数向较大数靠拢。如FLOOR -2.35变成-2.4，而DOWN-2.35变成-2.3
         */
        @JvmStatic
        fun divide(value1: String, value2: String, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.DOWN): String {
            //如果精确范围小于0，抛出异常信息
            if (scale < 0) {
                throw IllegalAccessException("精确度不能小于0")
            }
            val b1 = BigDecimal(value1)
            val b2 = BigDecimal(value2)
            return b1.divide(b2, scale, roundingMode).toString()
        }
    }
}