package com.duongnh.testapp2024.domain.usecase

import com.duongnh.testapp2024.data.WrappedListResponse
import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.data.remote.dto.UserResponse
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserEntity
import com.duongnh.testapp2024.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(params: UserRequest): Flow<Result<List<UserEntity>, WrappedListResponse<UserResponse>>> =
        userRepository.getUsers(params)
}