package com.duongnh.testapp2024.domain.repository

import com.duongnh.testapp2024.data.WrappedListResponse
import com.duongnh.testapp2024.data.WrappedResponse
import com.duongnh.testapp2024.data.remote.dto.UserDetailResponse
import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.data.remote.dto.UserResponse
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Defines the user repository to get the users and user detail
 */
interface UserRepository {
    suspend fun getUsers(userRequest: UserRequest): Flow<Result<List<UserEntity>, WrappedListResponse<UserResponse>>>
    suspend fun getUserDetail(userName: String): Flow<Result<UserDetailEntity, WrappedResponse<UserDetailResponse>>>
}