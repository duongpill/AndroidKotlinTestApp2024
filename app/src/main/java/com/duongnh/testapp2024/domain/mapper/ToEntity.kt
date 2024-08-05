package com.duongnh.testapp2024.domain.mapper

import com.duongnh.testapp2024.data.remote.dto.UserDetailResponse
import com.duongnh.testapp2024.data.remote.dto.UserResponse
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity

fun UserResponse.toEntity() = UserEntity(id ?: 0, userName ?: "", avatarUrl ?: "", htmlUrl ?: "")

fun UserDetailResponse.toEntity() = UserDetailEntity(
    id ?: 0,
    userName ?: "",
    avatarUrl ?: "",
    htmlUrl ?: "",
    location ?: "",
    followers ?: 0,
    following ?: 0
)