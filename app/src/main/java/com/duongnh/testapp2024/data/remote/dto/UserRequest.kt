package com.duongnh.testapp2024.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Pass the pagination params in this class
 */
data class UserRequest(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int
)