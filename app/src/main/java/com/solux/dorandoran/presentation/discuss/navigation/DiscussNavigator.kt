package com.solux.dorandoran.presentation.discuss.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class DiscussNavigator(
    val navController: NavHostController
){

    fun navigateToDiscussionRoom(discussionId:Int) {
        navController.navigate("discussionRoom/$discussionId")
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}

