package com.duongnh.testapp2024.usecase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.repository.UserRepository
import com.duongnh.testapp2024.domain.usecase.GetUserDetailUseCase
import com.duongnh.testapp2024.repository.FakeUserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetUserDetailUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var userRepository: UserRepository

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Before
    fun setup() {
        userRepository = FakeUserRepository()
        getUserDetailUseCase = GetUserDetailUseCase(userRepository)
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Completed() = runBlocking {
        getUserDetailUseCase.invoke("Duong").collect {
            assert(it is Result.Success)
        }
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Success() = runBlocking {
        getUserDetailUseCase.invoke("Duong").collect {
            assert(it is Result.Success && it.data.userName == "Duong")
        }
    }

    @Test
    fun testGetUsersUseCase_GetUsers_Error() = runBlocking {
        getUserDetailUseCase.invoke("Duong").collect {
            assert(it is Result.Error)
        }
    }
}