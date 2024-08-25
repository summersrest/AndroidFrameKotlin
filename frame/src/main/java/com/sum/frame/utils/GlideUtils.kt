package com.sum.frame.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sum.frame.SFrame.Companion.context
import com.sum.frame.R

/**
 * @author  LiuJiang
 * Desc:    Glide工具类
 */
class GlideUtils private constructor() {
    companion object {

        /**
         * 加载图片
         */
        @JvmStatic
        fun loadImage(url: String, imageView: ImageView, placeHolderResource: Int?, errorResource: Int?) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(placeHolderResource ?: R.drawable.img_error)
                .error(errorResource ?: R.drawable.img_error)
                .into(imageView)
        }

        /**
         * 加载圆形图片
         */
        @JvmStatic
        fun loadCircleImage(url: String, imageView: ImageView, placeHolderResource: Int?, errorResource: Int?) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .placeholder(placeHolderResource ?: R.drawable.img_error)
                .error(errorResource ?: R.drawable.img_error)
                .into(imageView)
        }

        /**
         * 加载圆角图片
         */
        @JvmStatic
        fun loadRoundRectangleImage(
            url: String,
            imageView: ImageView,
            radius: Int,
            placeHolderResource: Int? = null,
            errorResource: Int? = null
        ) {
            Glide.with(context)
                .load(url)
                .transform(CenterCrop(), RoundedCorners(ScreenUtils.dp2px(radius)))
                .placeholder(placeHolderResource ?: R.drawable.img_error)
                .error(errorResource ?: R.drawable.img_error)
                .into(imageView)

        }
    }
}