package com.sum.frame.mvp

import com.sum.frame.http.HttpUtils


/**
 * @author  LiuJiang
 * Desc:
 */
open class BaseModel(protected var tag: Any?){
    protected val httpUtils = HttpUtils.instance

}