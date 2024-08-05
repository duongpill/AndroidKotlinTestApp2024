package com.duongnh.testapp2024.data.remote.api

import com.duongnh.testapp2024.data.remote.dto.UserDetailResponse
import com.duongnh.testapp2024.data.remote.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    /**
     * Get all users in the pagination
     */
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<UserResponse>>

    /**
     * Use the userName to get the user detail
     */
    @GET("users/{login_username}")
    suspend fun getUserDetails(@Path("login_username") userName: String): Response<UserDetailResponse>
}