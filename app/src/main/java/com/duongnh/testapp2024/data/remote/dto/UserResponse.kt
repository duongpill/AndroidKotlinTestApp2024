package com.duongnh.testapp2024.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Define to get the users response
 * And add the safe call (?) so we can get the null data
 */
data class UserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?
)