package com.sum.frame.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author  LiuJiang
 * Desc:    数字工具类
 */
class FigureUtils private constructor() {
    companion object {

        /**
         * 判断字符串是否为整数
         */
        @JvmStatic
        fun isLong(input: String): Boolean = input.toLongOrNull() != null

        /**
         * 判断字符串是否为数字
         */
        @JvmStatic
        fun isDouble(input: String): Boolean = input.toDoubleOrNull() != null

        /**
         * 设置数字类型的小数的精度
         * @param input         输入内容
         * @param scale         保留精度位数
         * @param isZeroFill    不够设置的精度是是否在末尾补零，默认不补。
         * @param roundingMode  精确方式：(假如scale = 1，保留一位小数前提下)
         *                                RoundingMode.DOWN：直接删除多余的小数位，如2.35会变成2.3
         *                                RoundingMode.UP：进位处理，2.35变成2.4
         *                                RoundingMode.HALF_UP：四舍五入，2.35变成2.4
         *                                RoundingMode.HALF_UP：五舍六入，是5则向下舍，2.35变成2.4
         *                                RoundingMode.HALF_EVEN：四舍五入，是5则向偶数邻居靠拢。2.35变成2.4，2.45变成2.4
         *                                RoundingMode.CEILING：向较大的数进位，与UP的区别是负数向较大的数靠拢，而UP的负数向较大数靠拢。如CEILING -2.35变成-2.3，而UP-2.35变成-2.4
         *                                RoundingMode.FLOOR：向较小的数进位，与DOWN的区别是负数向较小的数靠拢，而DOWN的负数向较大数靠拢。如FLOOR -2.35变成-2.4，而DOWN-2.35变成-2.3
         */
        @JvmStatic
        fun setScale(input: String, scale: Int, isZeroFill: Boolean, roundingMode: RoundingMode = RoundingMode.DOWN): String {
            val result = BigDecimal(input).setScale(scale, roundingMode)
            return if (isZeroFill)
                result.toString()
            else
                result.stripTrailingZeros().toString()
        }
    }
}