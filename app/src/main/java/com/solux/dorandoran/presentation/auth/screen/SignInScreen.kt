package com.solux.dorandoran.presentation.auth.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.largeBold
import com.solux.dorandoran.presentation.auth.navigation.AuthNavigator

@Composable
fun SignInRoute(navigator: AuthNavigator) {
    SignInScreen(navigator = navigator)
}

@Composable
fun SignInScreen(
    navigator: AuthNavigator,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val naverLoginUrl =
        "http://ec2-15-164-67-216.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/naver"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background02)
            .padding(horizontal = 10.dp)
            .padding(top = 180.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(bottom = 25.dp)
        )

        Text(
            text = "도란도란",
            style = largeBold,
            color = Button02
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.btn_naver),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, naverLoginUrl.toUri())
                    context.startActivity(intent)
                }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.btn_google),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable {
                    // 구글 로그인 로직
                }
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}
