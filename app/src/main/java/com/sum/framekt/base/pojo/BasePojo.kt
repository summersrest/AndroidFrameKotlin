package com.sum.framekt.base.pojo

import java.io.Serializable

/**
 * @author  LiuJiang
 * Desc:    解析类
 */
data class BasePojo<T>(var error: Int, var data: T?, var message: String) : Serializable
