package com.pactera.frame.base.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * @author liujiang
 * Desc:
 */
object GlideUtils {
    fun load(context: Context?, url: String?, imageView: ImageView?) {
        val options = RequestOptions()
        options.centerCrop()
        //防止图片背景变绿
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        //        options.error(R.mipmap.img_error);
//        options.placeholder(R.mipmap.img_error);
        options.dontAnimate()
        Glide.with(context!!).load(url).apply(options).into(imageView!!)
    }
}