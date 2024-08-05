package com.duongnh.testapp2024.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Define to get the user detail response
 * And add the safe call (?) so we can get the null data
 */
data class UserDetailResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("followers") val followers: Int?,
    @SerializedName("following") val following: Int?
)