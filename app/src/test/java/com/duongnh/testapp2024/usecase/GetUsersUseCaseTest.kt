package com.duongnh.testapp2024.usecase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.repository.UserRepository
import com.duongnh.testapp2024.domain.usecase.GetUsersUseCase
import com.duongnh.testapp2024.repository.FakeUserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetUsersUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var userRepository: UserRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setup() {
        userRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Completed() = runBlocking {
        getUsersUseCase.invoke(UserRequest(1, 20)).collect {
            assert(it is Result.Success)
        }
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Success() = runBlocking {
        getUsersUseCase.invoke(UserRequest(1, 20)).collect {
            assert(it is Result.Success && it.data.isNotEmpty())
        }
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Error() = runBlocking {
        getUsersUseCase.invoke(UserRequest(1, 20)).collect {
            assert(it is Result.Error)
        }
    }
}