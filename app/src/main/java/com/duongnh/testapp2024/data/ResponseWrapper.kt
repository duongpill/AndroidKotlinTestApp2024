package com.duongnh.testapp2024.data

import com.google.gson.annotations.SerializedName

/**
 * Wrap the retrofit response with List
 */
data class WrappedListResponse<T>(
    var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: List<T>? = null
)

/**
 * Wrap the retrofit response with the Object
 */
data class WrappedResponse<T>(
    var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: T? = null
)