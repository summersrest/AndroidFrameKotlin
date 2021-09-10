package com.pactera.frame.base.mvp

import android.content.Context
import androidx.fragment.app.Fragment
import com.pactera.frame.base.http.HttpUtils

/**
 * @author liujiang
 * Desc: BaseModel
 */
open class BaseModel constructor(protected var context: Context) {
    private var httpUtils: HttpUtils? = null

    init {
        httpUtils = HttpUtils.instance
    }

}