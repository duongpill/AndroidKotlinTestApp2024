package com.duongnh.testapp2024.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.duongnh.testapp2024.domain.entity.UserEntity

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    onUserTapped: (String) -> Unit,
    onCloseUserDetail: () -> Unit,
    loadMore: () -> Unit
) {
    HomeScreenWithList(
        uiState = uiState,
        modifier = modifier,
        onCloseUserDetail = onCloseUserDetail
    ) { contentPadding, contentModifier ->
        if (uiState.isUserDetailOpen) {
            Crossfade(targetState = uiState.userDetail, label = "") { userDetail ->
                key(userDetail.id) {
                    BackHandler {
                        onCloseUserDetail()
                    }
                    UserDetailScreen(
                        modifier = modifier,
                        contentPadding = contentPadding,
                        userDetail = userDetail
                    )
                }
            }
        } else {
            UserList(
                users = uiState.users,
                contentPadding = contentPadding,
                isLoading = uiState.isLoading,
                onUserTapped = onUserTapped,
                modifier = contentModifier,
                loadMore = loadMore
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenWithList(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    onCloseUserDetail: () -> Unit,
    hasUsersContent: @Composable (
        contentPadding: PaddingValues,
        modifier: Modifier
    ) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarState = topAppBarState,
                isUserDetailOpen = uiState.isUserDetailOpen
            ) {
                onCloseUserDetail()
            }
        },
        modifier = modifier
    )
    { innerPadding ->
        val contentModifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        LoadingContent(
            isLoading = uiState.users.isEmpty() && uiState.isLoading, // Only show it when the list is empty
            loading = {
                ScreenLoading()
            },
            content = {
                if (uiState.users.isEmpty()) {
                    Box(
                        contentModifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "No Users Found!!",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                } else {
                    hasUsersContent(innerPadding, contentModifier)
                }
            }
        )
    }
}

@Composable
private fun LoadingContent(
    isLoading: Boolean,
    loading: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        loading()
    } else {
        content()
    }
}

@Composable
private fun UserList(
    modifier: Modifier = Modifier,
    users: List<UserEntity>,
    isLoading: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onUserTapped: (userName: String) -> Unit,
    loadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(reachedBottom) {
        if(reachedBottom) {
            loadMore()
        }
    }

    LazyColumn(modifier = modifier, contentPadding = contentPadding, state = listState) {
        items(items = users, key = { it.id }) {user ->
            UserCard(user = user, navigateToDetail = onUserTapped)
            Spacer(modifier.size(15.dp))
        }
        if(isLoading) {
            item {
                LoadingMore()
            }
        }
    }
}

@Composable
private fun ScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(40.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun LoadingMore(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(40.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}