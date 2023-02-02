package com.sum.framekt.application.pojo

/**
 * @author  LiuJiang
 * created  at: 2023/1/22 14:22
 * Desc:
 */
data class UserBean(
    var age: Int?,
    var job: List<Job?>?,
    var nickName: String?,
    var userId: String?,
    var userName: String?
)

data class Job(
    var id: Int?,
    var name: String?
)