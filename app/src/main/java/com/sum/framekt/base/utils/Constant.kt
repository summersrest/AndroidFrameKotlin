package com.sum.framekt.base.utils

/**
 * @author  LiuJiang
 * Desc:    常量表
 */
class Constant {
    companion object {
        const val ACCESS_TOKEN = "access_token"

        //上次token刷新时间
        const val TOKEN_REFRESH_TIME = "token_refresh_time"

        //TOKEN有效期
        const val VALIDITY = (1000 * 60 * 60 - 1000 * 60).toLong()

        //用户名
        const val USERNAME = "userName"

        //密码
        const val PWD = "pwd"
    }
}