package com.duongnh.testapp2024.repository

import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity
import com.duongnh.testapp2024.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class FakeUserRepository : UserRepository {

    private val fakeUsers = listOf(
        UserEntity(1, "Duong", "https://123.321", "https://123.321"),
        UserEntity(2, "Dung", "https://123.321", "https://123.321"),
        UserEntity(3, "Hai", "https://123.321", "https://123.321"),
        UserEntity(4, "Kien", "https://123.321", "https://123.321")
    )

    private val fakeUserDetail =
        UserDetailEntity(1, "Duong", "https://123.321", "https://123.321", "Ho Chi Minh", 1000, 100)

    override suspend fun getUsers(userRequest: UserRequest) = flow {
        emit(Result.Success(fakeUsers))
    }

    override suspend fun getUserDetail(userName: String) = flow {
        emit(Result.Success(fakeUserDetail))
    }
}