package com.sum.framekt.base.mvp

import android.content.Context
import androidx.fragment.app.Fragment
import com.sum.framekt.base.http.HttpUtils

/**
 * @author  LiuJiang
 * Desc:
 */
open class BaseModel(protected var context: Context?) {
    protected var httpUtils = HttpUtils.instance
    protected var fragment: Fragment? = null

    constructor(fragment: Fragment?) : this(context = null) {
        this.fragment = fragment
    }
}