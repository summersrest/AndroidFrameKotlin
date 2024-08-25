package com.sum.androidframekt.entity

data class ArticleEntity(
    val id: Int = 0,
    val images: List<String> = listOf(),
    val likes: Int = 0,
    val readings: Int = 0,
    val text: String = "",
    val timestamp: String = "",
    val userId: Int = 0,
    val userImageUrl: String = "",
    val userNickname: String = ""
)