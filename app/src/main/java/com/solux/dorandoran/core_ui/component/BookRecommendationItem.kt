package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral70
import com.solux.dorandoran.core_ui.theme.smallBold
import com.solux.dorandoran.core_ui.theme.smallRegular02
import com.solux.dorandoran.domain.entity.BookEntity

@Composable
fun BookRecommendationItem(
    book: BookEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(88.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .size(88.dp, 127.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Background03),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = book.title,
                style = smallBold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.title,
            style = smallBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = book.author,
            style = smallRegular02,
            color = Neutral70,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            repeat(5) { index ->
                Image(
                    painter = painterResource(
                        id = if (index < book.rating) {
                            R.drawable.ic_home_star_fill
                        } else {
                            R.drawable.ic_home_star
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}