package com.duongnh.testapp2024.domain.entity

data class UserEntity(
    override val id: Int,
    override val userName: String,
    override val avatarUrl: String,
    override val htmlUrl: String
): BaseUserEntity