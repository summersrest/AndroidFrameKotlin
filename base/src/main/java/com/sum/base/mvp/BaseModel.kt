package com.sum.base.mvp

import com.sum.base.http.HttpUtils


/**
 * @author  LiuJiang
 * Desc:
 */
open class BaseModel(protected var tag: Any?){
    protected val httpUtils = HttpUtils.instance

}