package com.duongnh.testapp2024.domain.entity

data class UserDetailEntity(
    override val id: Int,
    override val userName: String,
    override val avatarUrl: String,
    override val htmlUrl: String,
    val location: String,
    val followers: Int,
    val following: Int
): BaseUserEntity {
    constructor(): this(0, "", "", "", "", 0, 0)
}