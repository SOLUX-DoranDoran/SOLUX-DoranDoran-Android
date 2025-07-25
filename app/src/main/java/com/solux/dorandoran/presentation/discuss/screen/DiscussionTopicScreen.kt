package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.DiscussionTopicBox
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.domain.entity.DiscussionArgument
import com.solux.dorandoran.presentation.discuss.DiscussViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview  
import com.solux.dorandoran.core_ui.component.ArgumentInputBox
import com.solux.dorandoran.core_ui.component.DiscussionCommentBox
import com.solux.dorandoran.domain.entity.Comment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscussionTopicScreen(
    discussion: DiscussionPageEntity,
    argument: DiscussionArgument,
    onBackClick:()->Unit={},
    viewModel: DiscussViewModel = hiltViewModel(),
    comment: Comment,
    onItemClick: (DiscussionPageEntity) -> Unit
) {
    val commentInputMap by viewModel.activeCommentInputMap
    val isInputVisible = commentInputMap[argument.id] == true
    var commentText by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = discussion.bookTitle,
                        style = baseBold,
                        color = Neutral60
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "이전 화면")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background02
                )
            )
        },
        containerColor = Background02,

        bottomBar = {
            if (isInputVisible) {
                ArgumentInputBox(
                    inputText = commentText,
                    onInputChange = { commentText = it },
                    modifier = Modifier.padding(8.dp),
                    onSubmit = {
                        viewModel.submitComment(argument.id, commentText)
                        commentText = "" // 입력창 초기화
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                DiscussionTopicBox(
                    discussion = discussion,
                    onClick = {},
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    argument = argument
                )
            }

            item {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
            }

            items(discussion.arguments) { comment ->
                DiscussionCommentBox(
                    discussion = discussion,
                    argument = argument,
                    comments = argument.comments,
                    onAddComment = { viewModel.toggleCommentInput(argument.id) },
                    isInputVisible = commentInputMap[argument.id] == true
                )
            }


        }
    }
}