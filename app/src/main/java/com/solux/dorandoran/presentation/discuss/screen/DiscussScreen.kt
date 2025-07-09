package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.component.DiscussionBox
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import androidx.compose.ui.tooling.preview.Preview as Preview


@Composable
fun DiscussRoute(
    navigator: DiscussNavigator
) {

    val sampleDiscussions = listOf(
        DiscussionPageEntity(
            id = 1,
            bookTitle = "로미오와 줄리엣",
            discussionTopic = "둘은 찐 사랑이 맞는가",
            bookImageUrl = "",
            authorName = "이눈송"
        ),
        DiscussionPageEntity(
            id = 2,
            bookTitle = "해리포터",
            discussionTopic = "덤블도어는 옳았는가",
            bookImageUrl = "",
            authorName = "김코딩"
        )
    )
    DiscussScreen(
        discussionEx = sampleDiscussions,
        onItemClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscussScreen(

    discussionEx: List<DiscussionPageEntity>,
    onItemClick: (DiscussionPageEntity) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "토론",
                    style = baseBold,
                    color = Neutral60
                    ) },
                actions = {
                    IconButton(onClick = { /* TODO: 검색 기능 */ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "검색")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background03
                )
            )
        },
        containerColor = Background03,
        modifier = Modifier,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top=40.dp)

        ) {
            items(discussionEx) { discussion ->
                DiscussionBox(
                    discussion = discussion,
                    onClick = { },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}


    @Preview(showBackground = true)
    @Composable
    fun DiscussScreenPreview() {

        val discussionEx = listOf(
            DiscussionPageEntity(
                id = 1,
                bookTitle = "로미오와 줄리엣",
                discussionTopic = "둘은 찐 사랑이 맞는가",
                bookImageUrl = "",
                authorName = "이눈송"
            ),
            DiscussionPageEntity(
                id = 2,
                bookTitle = "해리포터",
                discussionTopic = "덤블도어는 옳았는가",
                bookImageUrl = "",
                authorName = "김코딩"
            )
        )


        DoranDoranTheme {
            DiscussScreen(
                discussionEx = discussionEx,
                onItemClick = {}
            )
        }
    }

