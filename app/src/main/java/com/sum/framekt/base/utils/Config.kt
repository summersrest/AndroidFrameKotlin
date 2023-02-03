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

        const val USER_INFO: String = "/v1/getInfoById"

        const val USER_LIST: String = "/v1/user/list"

        const val USER_LIST_BY_PAGE: String = "/v1/user/listByPage"
    }
}