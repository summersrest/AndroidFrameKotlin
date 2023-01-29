package com.sum.framekt.application.pages

import android.content.Context
import com.sum.framekt.base.mvp.BaseModel

/**
 * @author  liujiang
 * created  at: 2023/1/20 16:58
 * Desc:
 */
class MainModel(context: Context?) : BaseModel(context) {
    fun getRequest() : String {
        return "result"

    }
}