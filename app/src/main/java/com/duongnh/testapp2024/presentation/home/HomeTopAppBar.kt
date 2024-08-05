package com.duongnh.testapp2024.presentation.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duongnh.testapp2024.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(
        topAppBarState
    ),
    isUserDetailOpen: Boolean,
    onBackPressed: () -> Unit
) {
    val title =
        stringResource(id = if (!isUserDetailOpen) R.string.home_title else R.string.user_detail_title)

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (isUserDetailOpen) {
                FilledIconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = modifier.size(14.dp)
                    )
                }
            }
        }
    )
}