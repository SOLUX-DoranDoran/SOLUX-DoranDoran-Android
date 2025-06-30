package com.solux.dorandoran.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.solux.dorandoran.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

val largeBold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 24.sp,
)
val largeRegular = TextStyle(
    fontFamily = pretendardRegular,
    fontSize = 24.sp,
)
val baseBold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 18.sp,
)
val baseRegular = TextStyle(
    fontFamily = pretendardRegular,
    fontSize = 18.sp,
)
val smallBold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 14.sp,
)
val smallRegular = TextStyle(
    fontFamily = pretendardRegular,
    fontSize = 14.sp,
)
val smallBold02 = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 12.sp,
)
val smallRegular02 = TextStyle(
    fontFamily = pretendardRegular,
    fontSize = 12.sp,
)


