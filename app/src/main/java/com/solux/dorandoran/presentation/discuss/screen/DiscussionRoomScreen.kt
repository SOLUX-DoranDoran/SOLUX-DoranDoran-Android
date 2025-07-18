package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.DiscussionBookBox
import com.solux.dorandoran.core_ui.component.DiscussionRoomBox
import com.solux.dorandoran.core_ui.component.DiscussionBox
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.largeBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.presentation.discuss.DiscussViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.ui.tooling.preview.Preview as Preview



@Composable
fun DiscussionRoomRoute(
    navigator: DiscussNavigator,
    discussionId: Int,
    viewModel: DiscussViewModel = hiltViewModel()
) {


    val selectedDiscussion = viewModel.getDiscussionById(discussionId)


    if (selectedDiscussion != null) {
        val bookDiscussions = viewModel.getDiscussionsForBook(selectedDiscussion.bookTitle)
        DiscussionRoomScreen(
            selectedBook = selectedDiscussion,
            discussionsForBook = bookDiscussions,
            onBackClick={navigator.navigateUp()},
            onAddClick={},
            onDiscussionClick={ clickedDiscussionId ->
                navigator.navigateToDiscussionRoom(clickedDiscussionId)
            }
        )
    }
    else {
        Text ("선택된 책을 찾을 수 없습니다")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscussionRoomScreen(
    selectedBook: DiscussionPageEntity,
    discussionsForBook: List<DiscussionPageEntity>,
    onBackClick:()->Unit={},
    onAddClick:()->Unit={},
    onDiscussionClick:(Int)->Unit={}

) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "토론방",
                        style = baseBold,
                        color = Neutral60
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "이전 화면")
                    }
                },
                actions = {
                    IconButton(onClick = onAddClick) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "토론 추가")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background02
                )
            )
        },
        containerColor = Background02,
        modifier = Modifier,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical=8.dp)

        ) {
            item {
                DiscussionBookBox(
                    discussion = selectedBook,
                    modifier = Modifier
                        .padding(8.dp),
                    onClick = {}
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.width(5.dp))
            }

            item {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(Neutral60)) {
                            append("활성화된 ")
                        }
                        withStyle(style = SpanStyle(Button02)) {
                            append("토론")
                        }
                    },
                    modifier = Modifier.padding(start = 20.dp),
                    style = largeBold
                )
            }

            items(discussionsForBook) { discussion ->
                DiscussionRoomBox(
                    discussion = discussion,
                    onClick = { onDiscussionClick(discussion.id) },
                    modifier = Modifier
                        .padding(15.dp)
                )
            }

        }
    }
}