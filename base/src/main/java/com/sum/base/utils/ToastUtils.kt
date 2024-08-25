package com.sum.base.utils

import android.content.res.Resources
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.sum.base.SFrame
import com.sum.base.databinding.ViewToastBinding


/**
 * @author  LiuJiang
 * Desc:    Toast工具类
 */
class ToastUtils private constructor() {
    companion object {
        private var toast: Toast? = null

        /**
         * 弹出字符串资源的Toast
         */
        @JvmStatic
        fun show(resId: Int, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.BOTTOM) {
            try {
                show(SFrame.context.getString(resId), duration, gravity)
            } catch (e: Resources.NotFoundException) {
                show(resId.toString(), duration, gravity)
            }
        }

        /**
         * 弹出某个字符串的Toast
         */
        @JvmStatic
        fun show(message: String?, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.BOTTOM) {
            if (message.isNullOrBlank()) return
            toast?.cancel()
            if (null == toast) toast = Toast(SFrame.context)
            //时长
            toast?.duration = duration
            //android 11 以上不支持自定义
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                //自定义布局
                val binding = ViewToastBinding.inflate(LayoutInflater.from(SFrame.context))
                binding.tvText.text = message
                toast?.view = binding.root
            } else {
                toast?.setText(message)
            }
            //设置Toast的位置
            toast?.setGravity(gravity, 0, 200)
            //弹出
            toast?.show()
        }
    }
}