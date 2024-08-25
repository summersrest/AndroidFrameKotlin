package com.sum.sample.entity

data class UserInfoEntity(
    val address: List<String> = listOf(),
    val birthday: String = "",
    val email: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val imageUrl: String = "",
    val nickname: String = "",
    val password: String = "",
    val phone: String = "",
    val token: String = "",
    val username: String = ""
)