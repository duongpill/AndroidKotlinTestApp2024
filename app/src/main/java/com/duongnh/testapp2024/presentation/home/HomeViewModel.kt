package com.duongnh.testapp2024.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duongnh.testapp2024.data.remote.dto.UserRequest
import com.duongnh.testapp2024.domain.Result
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity
import com.duongnh.testapp2024.domain.usecase.GetUserDetailUseCase
import com.duongnh.testapp2024.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _uiState = MutableStateFlow(
        HomeUIState(isLoading = true)
    )

    private var currentPage = 1
    private var totalItemInPage = 20

    init {
        fetchUsers(currentPage, totalItemInPage)
    }

    // UI state exposed to the UI
    val uiState: StateFlow<HomeUIState> = _uiState

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    fun fetchUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            getUsersUseCase.invoke(UserRequest(page, perPage))
                .onStart {
                    setLoading(true)
                }
                .catch { ex ->
                    setLoading(false)
                    Log.e(TAG, ex.message.toString())
                }
                .collect { result ->
                    _uiState.update { state ->
                        when (result) {
                            is Result.Success -> {
                                state.copy(users = result.data, isLoading = false)
                            }

                            is Result.Error -> {
                                state.copy(
                                    isLoading = false,
                                    errorMessage = result.rawResponse.message
                                )
                            }
                        }
                    }

                }
        }
    }

    fun loadMoreItems(){
        currentPage += currentPage
        totalItemInPage += totalItemInPage
        fetchUsers(currentPage, totalItemInPage)
    }

    private fun getUserDetail(userName: String) {
        viewModelScope.launch {
            getUserDetailUseCase.invoke(userName)
                .onStart {
                    setLoading(true)
                }
                .catch { ex ->
                    setLoading(false)
                    Log.e(TAG, ex.message.toString())
                }
                .collect { result ->
                    _uiState.update { state ->
                        when (result) {
                            is Result.Success -> {
                                state.copy(
                                    isLoading = false,
                                    isUserDetailOpen = true,
                                    userDetail = result.data
                                )
                            }

                            is Result.Error -> {
                                state.copy(
                                    isLoading = false,
                                    errorMessage = result.rawResponse.message
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onClickUser(userName: String) {
        getUserDetail(userName)
    }

    fun closeDetailScreen() {
        _uiState.update {
            it.copy(isUserDetailOpen = false)
        }
    }
}

data class HomeUIState(
    val users: List<UserEntity> = emptyList(),
    val isUserDetailOpen: Boolean = false,
    val userDetail: UserDetailEntity = UserDetailEntity(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)