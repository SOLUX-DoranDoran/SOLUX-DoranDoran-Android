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
                start = 6.dp, // 왼쪽 꼬리일 때 패딩 추가
                end = 6.dp,   // 오른쪽 꼬리일 때 패딩 추가
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
    val tailOffset = 20.dp.toPx() // 모서리에서 꼬리까지의 거리

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

    // 메인 말풍선 영역 (꼬리 공간 제외)
    val bubbleHeight = size.height - tailHeight

    if (isRightTail) {
        // 오른쪽 꼬리가 있는 경우
        val bubbleWidth = size.width - tailWidth

        // 메인 버블 (둥근 사각형)
        path.addRoundRect(
            RoundRect(
                rect = Rect(0f, 0f, bubbleWidth, bubbleHeight),
                cornerRadius = CornerRadius(cornerRadius)
            )
        )

        // 오른쪽 꼬리 - 둥근 모양
        val tailStartX = bubbleWidth - tailOffset
        val tailStartY = bubbleHeight

        // 꼬리의 시작점에서 둥근 곡선으로 꼬리 그리기
        path.moveTo(tailStartX, tailStartY)

        // 베지어 곡선을 사용하여 둥근 꼬리 생성
        val controlPoint1X = tailStartX + tailWidth * 0.3f
        val controlPoint1Y = tailStartY + tailHeight * 0.3f
        val controlPoint2X = tailStartX + tailWidth * 0.7f
        val controlPoint2Y = tailStartY + tailHeight * 0.7f
        val endPointX = tailStartX + tailWidth * 0.5f
        val endPointY = tailStartY + tailHeight

        path.cubicTo(
            controlPoint1X, controlPoint1Y,
            controlPoint2X, controlPoint2Y,
            endPointX, endPointY
        )

        // 돌아오는 곡선
        val backControlPoint1X = tailStartX + tailWidth * 0.2f
        val backControlPoint1Y = tailStartY + tailHeight * 0.8f
        val backControlPoint2X = tailStartX - tailWidth * 0.1f
        val backControlPoint2Y = tailStartY + tailHeight * 0.4f

        path.cubicTo(
            backControlPoint1X, backControlPoint1Y,
            backControlPoint2X, backControlPoint2Y,
            tailStartX - tailWidth * 0.3f, tailStartY
        )

        path.close()

    } else {
        // 왼쪽 꼬리가 있는 경우
        val bubbleStartX = tailWidth
        val bubbleWidth = size.width - tailWidth

        // 메인 버블 (둥근 사각형)
        path.addRoundRect(
            RoundRect(
                rect = Rect(bubbleStartX, 0f, size.width, bubbleHeight),
                cornerRadius = CornerRadius(cornerRadius)
            )
        )

        // 왼쪽 꼬리 - 둥근 모양
        val tailStartX = bubbleStartX + tailOffset
        val tailStartY = bubbleHeight

        path.moveTo(tailStartX, tailStartY)

        // 베지어 곡선을 사용하여 둥근 꼬리 생성
        val controlPoint1X = tailStartX - tailWidth * 0.3f
        val controlPoint1Y = tailStartY + tailHeight * 0.3f
        val controlPoint2X = tailStartX - tailWidth * 0.7f
        val controlPoint2Y = tailStartY + tailHeight * 0.7f
        val endPointX = tailStartX - tailWidth * 0.5f
        val endPointY = tailStartY + tailHeight

        path.cubicTo(
            controlPoint1X, controlPoint1Y,
            controlPoint2X, controlPoint2Y,
            endPointX, endPointY
        )

        // 돌아오는 곡선
        val backControlPoint1X = tailStartX - tailWidth * 0.2f
        val backControlPoint1Y = tailStartY + tailHeight * 0.8f
        val backControlPoint2X = tailStartX + tailWidth * 0.1f
        val backControlPoint2Y = tailStartY + tailHeight * 0.4f

        path.cubicTo(
            backControlPoint1X, backControlPoint1Y,
            backControlPoint2X, backControlPoint2Y,
            tailStartX + tailWidth * 0.3f, tailStartY
        )

        path.close()
    }

    return path
}