package com.duongnh.testapp2024.domain.usecase

import com.duongnh.testapp2024.data.WrappedResponse
import com.duongnh.testapp2024.data.remote.dto.UserDetailResponse
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(userName: String): Flow<Result<UserDetailEntity, WrappedResponse<UserDetailResponse>>> =
        userRepository.getUserDetail(userName)
}