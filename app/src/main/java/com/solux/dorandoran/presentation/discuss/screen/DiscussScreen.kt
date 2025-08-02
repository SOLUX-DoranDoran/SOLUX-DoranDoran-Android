package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.DiscussionBox
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.domain.entity.DiscussPageEntity
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.domain.entity.DiscussCommentEntity
import com.solux.dorandoran.domain.repository.DiscussRepository
import com.solux.dorandoran.presentation.discuss.viewmodel.ArgumentViewModel
import com.solux.dorandoran.presentation.discuss.viewmodel.DiscussViewModel


@Composable
fun DiscussRoute(
    navigator: DiscussNavigator,
    viewModel: DiscussViewModel = hiltViewModel()
) {
    DiscussScreen(navigator = navigator, viewModel = viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscussScreen(
    navigator: DiscussNavigator,
    viewModel: DiscussViewModel
) {
    val discussions by viewModel.discussions

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background02
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
                    argument = argument,
                    onClick = { onItemClick(discussion) },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}