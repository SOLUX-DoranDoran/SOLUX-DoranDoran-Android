package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral80
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.baseRegular

@Composable
fun CommentInputSection(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Background01)
            .border(
                width = 1.dp,
                color = Neutral80,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
            .padding(16.dp)
    ) {
        // 댓글 입력 헤더
        CommentInputHeader(onCancel = onCancel)

        Spacer(modifier = Modifier.height(12.dp))

        // 댓글 입력 필드와 전송 버튼
        CommentInputRow(
            value = value,
            onValueChange = onValueChange,
            onSubmit = {
                if (value.isNotBlank()) {
                    onSubmit()
                    keyboardController?.hide()
                }
            }
        )
    }
}

@Composable
private fun CommentInputHeader(
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "댓글 작성",
            style = baseBold,
            color = Neutral60
        )

        Text(
            text = "취소",
            style = baseRegular,
            color = Neutral80,
            modifier = Modifier.clickable { onCancel() }
        )
    }
}

@Composable
private fun CommentInputRow(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        CommentTextField(
            value = value,
            onValueChange = onValueChange,
            onSubmit = onSubmit,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        SendButton(
            onClick = onSubmit,
            enabled = value.isNotBlank()
        )
    }
}

@Composable
private fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = "댓글을 입력하세요",
                style = baseRegular,
                color = Neutral80
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Button02,
            unfocusedBorderColor = Neutral80,
            focusedContainerColor = Background01,
            unfocusedContainerColor = Background01
        ),
        shape = RoundedCornerShape(12.dp),
        textStyle = baseRegular.copy(color = Neutral60),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(
            onSend = { onSubmit() }
        ),
        singleLine = true
    )
}

@Composable
private fun SendButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.size(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Button02,
            contentColor = Background01,
            disabledContainerColor = Neutral80
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_reviewscreen_send),
            contentDescription = "댓글 전송",
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )
    }
}