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
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.DiscussionBox
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.presentation.discuss.DiscussViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun DiscussRoute(
    navigator: DiscussNavigator,
    viewModel: DiscussViewModel = hiltViewModel()
) {
    DiscussScreen(
        discussions = viewModel.sampleDiscussions,
        onItemClick = { discussion ->
            viewModel.selectDiscussion(discussion)
            navigator.navigateToDiscussionRoom(discussion.id)
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscussScreen(
    discussions: List<DiscussionPageEntity>,
    onItemClick: (DiscussionPageEntity) -> Unit,
    onSearchClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "토론",
                        style = baseBold,
                        color = Neutral60
                    )
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "검색",
                            tint = Neutral60
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background03
                )
            )
        },
        containerColor = Background03
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(discussions) { discussion ->
                DiscussionBox(
                    discussion = discussion,
                    onClick = { onItemClick(discussion) },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}