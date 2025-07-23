package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Background01

@Composable
fun BubbleCard(
    modifier: Modifier = Modifier,
    isOddPosition: Boolean = true, // true면 오른쪽 꼬리, false면 왼쪽 꼬리
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .width(363.dp)
            .wrapContentHeight()
            .drawBehind {
                drawBubbleShape(
                    size = size,
                    color = Background01,
                    isRightTail = isOddPosition
                )
            }
            .padding(
                start = if (isOddPosition) 6.dp else 6.dp, // 왼쪽 패딩은 동일
                end = if (isOddPosition) 2.dp else 6.dp,   // 홀수(오른쪽 꼬리)일 때 오른쪽 여백 축소
                bottom = 12.dp
            )
    ) {
        content()
    }
}

private fun DrawScope.drawBubbleShape(
    size: Size,
    color: Color,
    isRightTail: Boolean
) {
    val cornerRadius = 15.dp.toPx()
    val tailWidth = 12.dp.toPx()
    val tailHeight = 8.dp.toPx()
    val tailOffset = 340.dp.toPx()

    // 그림자를 위한 Paint 설정
    val shadowPaint = Paint().apply {
        this.color = Color.Black.copy(alpha = 0.1f)
        isAntiAlias = true
    }

    // 메인 카드를 위한 Paint 설정
    val cardPaint = Paint().apply {
        this.color = color
        isAntiAlias = true
    }

    drawIntoCanvas { canvas ->
        val shadowOffset = 2.dp.toPx()

        // 메인 버블과 꼬리를 함께 그리기
        val path = createBubblePath(size, cornerRadius, tailWidth, tailHeight, tailOffset, isRightTail)

        // 그림자 그리기
        val shadowPath = Path()
        shadowPath.addPath(path, Offset(0f, shadowOffset))
        canvas.drawPath(shadowPath, shadowPaint)

        // 메인 카드 그리기
        canvas.drawPath(path, cardPaint)
    }
}

private fun createBubblePath(
    size: Size,
    cornerRadius: Float,
    tailWidth: Float,
    tailHeight: Float,
    tailOffset: Float,
    isRightTail: Boolean
): Path {
    val path = Path()

    val bubbleHeight = size.height - tailHeight

    if (isRightTail) {
        val bubbleWidth = size.width - tailWidth

        path.addRoundRect(
            RoundRect(
                rect = Rect(0f, 0f, bubbleWidth, bubbleHeight),
                cornerRadius = CornerRadius(cornerRadius)
            )
        )

        val tailStartX = bubbleWidth - tailOffset
        val tailStartY = bubbleHeight

        path.moveTo(tailStartX, tailStartY)

        val controlPoint1X = tailStartX + tailWidth * 0.5f
        val controlPoint1Y = tailStartY + tailHeight * 0.3f
        val controlPoint2X = tailStartX + tailWidth * 0.9f
        val controlPoint2Y = tailStartY + tailHeight * 0.7f
        val endPointX = tailStartX + tailWidth * 0.7f
        val endPointY = tailStartY + tailHeight

        path.cubicTo(
            controlPoint1X, controlPoint1Y,
            controlPoint2X, controlPoint2Y,
            endPointX, endPointY
        )

        val backControlPoint1X = tailStartX + tailWidth * 0.4f
        val backControlPoint1Y = tailStartY + tailHeight * 0.6f
        val backControlPoint2X = tailStartX + tailWidth * 0.1f
        val backControlPoint2Y = tailStartY + tailHeight * 0.2f

        path.cubicTo(
            backControlPoint1X, backControlPoint1Y,
            backControlPoint2X, backControlPoint2Y,
            tailStartX + tailWidth * 0.1f, tailStartY
        )

        path.close()

    } else {
        val bubbleStartX = tailWidth
        val bubbleWidth = size.width - tailWidth

        path.addRoundRect(
            RoundRect(
                rect = Rect(bubbleStartX, 0f, size.width, bubbleHeight),
                cornerRadius = CornerRadius(cornerRadius)
            )
        )

        val tailStartX = bubbleStartX + tailOffset
        val tailStartY = bubbleHeight

        path.moveTo(tailStartX, tailStartY)

        val controlPoint1X = tailStartX - tailWidth * 0.5f
        val controlPoint1Y = tailStartY + tailHeight * 0.3f
        val controlPoint2X = tailStartX - tailWidth * 0.9f
        val controlPoint2Y = tailStartY + tailHeight * 0.7f
        val endPointX = tailStartX - tailWidth * 0.7f
        val endPointY = tailStartY + tailHeight

        path.cubicTo(
            controlPoint1X, controlPoint1Y,
            controlPoint2X, controlPoint2Y,
            endPointX, endPointY
        )

        val backControlPoint1X = tailStartX - tailWidth * 0.4f
        val backControlPoint1Y = tailStartY + tailHeight * 0.6f
        val backControlPoint2X = tailStartX - tailWidth * 0.1f
        val backControlPoint2Y = tailStartY + tailHeight * 0.2f

        path.cubicTo(
            backControlPoint1X, backControlPoint1Y,
            backControlPoint2X, backControlPoint2Y,
            tailStartX - tailWidth * 0.1f, tailStartY
        )

        path.close()
    }

    return path
}