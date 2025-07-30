package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseRegular

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = Neutral60, shape = RoundedCornerShape(24.dp))
            .background(Background01),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_search),
                    contentDescription = null,
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

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (searchText.isEmpty()) {
                        Text(
                            text = "검색하기",
                            style = baseRegular,
                            color = Neutral60
                        )
                    }

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
                        ),
                        decorationBox = { innerTextField ->
                            if (searchText.isNotEmpty()) {
                                innerTextField()
                            }
                        }
                    )
                }
            }

            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { searchText = "" }
                )
            }
        }
    }
}
