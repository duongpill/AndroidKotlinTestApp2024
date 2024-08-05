package com.duongnh.testapp2024.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.duongnh.testapp2024.domain.entity.BaseUserEntity
import com.duongnh.testapp2024.domain.entity.UserDetailEntity
import com.duongnh.testapp2024.domain.entity.UserEntity

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: BaseUserEntity,
    navigateToDetail: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .clip(CardDefaults.shape)
            .shadow(5.dp)
            .clickable(onClick = { navigateToDetail(user.userName) })
            .fillMaxWidth()
    ) {
        Row(modifier.padding(10.dp)) {
            ShowImage(url = user.avatarUrl)
            Column(modifier.padding(horizontal = 10.dp)) {
                Text(
                    modifier = modifier.padding(bottom = 10.dp),
                    text = user.userName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
                )
                UserContent(user = user)
            }
        }
    }
}

@Composable
private fun ShowImage(modifier: Modifier = Modifier, url: String) {
    Box(
        modifier = modifier
            .clip(CardDefaults.shape)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    ) {
        AsyncImage(
            modifier = modifier
                .size(80.dp, 80.dp)
                .padding(5.dp)
                .clip(CircleShape),
            model = url,
            contentDescription = null
        )
    }
}

@Composable
private fun UserContent(modifier: Modifier = Modifier, user: BaseUserEntity) {
    when (user) {
        is UserEntity -> {
            Text(
                modifier = modifier.padding(top = 10.dp),
                text = user.htmlUrl,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        is UserDetailEntity -> {
            Row(modifier.padding(top = 10.dp)) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )
                Text(
                    modifier = modifier.padding(start = 5.dp).align(Alignment.CenterVertically),
                    text = user.htmlUrl,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}