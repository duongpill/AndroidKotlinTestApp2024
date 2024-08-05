package com.duongnh.testapp2024.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(homeViewModel: HomeViewModel) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeRoute(
        uiState = uiState,
        onUserTapped = { homeViewModel.onClickUser(it) },
        onCloseUserDetail = { homeViewModel.closeDetailScreen() },
        loadMore = { homeViewModel.loadMoreItems() }
    )
}

@Composable
fun HomeRoute(
    uiState: HomeUIState,
    onUserTapped: (String) -> Unit,
    onCloseUserDetail: () -> Unit,
    loadMore: () -> Unit
) {
    HomeScreen(
        uiState = uiState,
        onUserTapped = onUserTapped,
        onCloseUserDetail = onCloseUserDetail,
        loadMore = loadMore
    )
}