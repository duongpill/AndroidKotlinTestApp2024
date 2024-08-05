package com.duongnh.testapp2024.data.repository

import com.duongnh.testapp2024.data.WrappedListResponse
import com.duongnh.testapp2024.data.WrappedResponse
import com.duongnh.testapp2024.data.remote.api.UserApi
import com.duongnh.testapp2024.data.remote.dto.UserDetailResponse
import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.data.remote.dto.UserResponse
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity
import com.duongnh.testapp2024.domain.mapper.toEntity
import com.duongnh.testapp2024.domain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implement the user repository
 */
class UserRepositoryImpl(private val userApi: UserApi) : UserRepository {

    override suspend fun getUsers(userRequest: UserRequest): Flow<Result<List<UserEntity>, WrappedListResponse<UserResponse>>> =
        flow {
            val response = userApi.getUsers(userRequest.page, userRequest.perPage)
            if (response.isSuccessful) {
                val data = response.body()
                val users: List<UserEntity> = data?.let { list ->
                    list.map { it.toEntity() }
                } ?: emptyList()
                emit(Result.Success(users))
            } else {
                val type = object : TypeToken<WrappedListResponse<UserResponse>>() {}.type
                val error = Gson().fromJson<WrappedListResponse<UserResponse>>(
                    response.errorBody()?.charStream(), type
                )
                error.code = response.code()
                emit(Result.Error(error))
            }
        }

    override suspend fun getUserDetail(userName: String): Flow<Result<UserDetailEntity, WrappedResponse<UserDetailResponse>>> =
        flow {
            val response = userApi.getUserDetails(userName)
            if (response.isSuccessful) {
                val userDetailEntity = response.body()?.toEntity()!!
                emit(Result.Success(userDetailEntity))
            } else {
                val type = object : TypeToken<WrappedResponse<UserDetailResponse>>() {}.type
                val error = Gson().fromJson<WrappedResponse<UserDetailResponse>>(
                    response.errorBody()?.charStream(), type
                )
                error.code = response.code()
                emit(Result.Error(error))
            }
        }

}