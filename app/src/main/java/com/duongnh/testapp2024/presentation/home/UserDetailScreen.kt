package com.duongnh.testapp2024.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.duongnh.testapp2024.domain.entity.UserDetailEntity

@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    userDetail: UserDetailEntity
) {
    LazyColumn(modifier = modifier, contentPadding = contentPadding) {
        item {
            Spacer(modifier.height(15.dp))
            UserCard(user = userDetail)
        }
        item {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Row {
                    ObservableSection(modifier, "${userDetail.followers}+", "Follower")
                    Spacer(modifier = modifier.width(25.dp))
                    ObservableSection(modifier, "${userDetail.following}+", "Following")
                }
            }
        }
        item {
            BlogSection(htmlUrl = userDetail.htmlUrl)
        }
    }
}

@Composable
private fun ObservableSection(modifier: Modifier = Modifier, text: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier.clip(CircleShape)) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = modifier.padding(15.dp)
            )
        }
        Text(
            modifier = modifier.padding(vertical = 5.dp),
            text = text,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BlogSection(modifier: Modifier = Modifier, htmlUrl: String) {
    Column(modifier.padding(horizontal = 15.dp)) {
        Text(
            text = "Blog",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = modifier.height(15.dp))
        Text(
            text = htmlUrl,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

