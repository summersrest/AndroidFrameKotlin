package com.sum.framekt.base.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * @author  LiuJiang
 * Desc:
 */
class GlideUtils {
    companion object {
        fun load(context: Context, url: String, imageView: ImageView) {
            val options: RequestOptions = RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate()
            Glide.with(context).load(url).apply(options).into(imageView)
        }
    }
}