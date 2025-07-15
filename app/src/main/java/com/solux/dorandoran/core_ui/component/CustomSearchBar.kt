package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.baseRegular
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Neutral60

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, Neutral60, RoundedCornerShape(24.dp))
            .background(Background01),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // 검색 아이콘 (검색 실행)
            Image(
                painter = painterResource(id = R.drawable.ic_home_search),
                contentDescription = "검색",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (searchText.isNotBlank()) {
                            onSearchClick(searchText)
                            keyboardController?.hide()
                        }
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 실제 입력 필드 (한국어 입력 문제 해결)
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = baseRegular.copy(color = Neutral60),
                    cursorBrush = SolidColor(Neutral60),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (searchText.isNotBlank()) {
                                onSearchClick(searchText)
                                keyboardController?.hide()
                            }
                        }
                    )
                )

                // Placeholder를 별도 Text로 분리
                if (searchText.isEmpty()) {
                    Text(
                        text = "검색하기",
                        style = baseRegular,
                        color = Neutral60
                    )
                }
            }
        }

        // 삭제 버튼 (오른쪽) - 텍스트가 있을 때만 표시
        if (searchText.isNotEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_delete),
                contentDescription = "지우기",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .clickable { searchText = "" }
            )
        }
    }
}