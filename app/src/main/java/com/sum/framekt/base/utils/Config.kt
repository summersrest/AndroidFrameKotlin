package com.sum.framekt.base.utils

import com.sum.framekt.BuildConfig

/**
 * @author  liuJiang
 * Desc:
 */
class Config {
    companion object {
        const val BASE_SERVICE: String = BuildConfig.BASE_URL

        const val LOGIN: String = "/v1/login"
    }
}