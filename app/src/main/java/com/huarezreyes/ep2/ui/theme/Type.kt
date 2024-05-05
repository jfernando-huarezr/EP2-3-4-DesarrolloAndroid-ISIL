package com.huarezreyes.ep2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.huarezreyes.ep2.R

// Set of Material typography styles to start with

private val Ydc = FontFamily(
    Font(
        R.font.ydcgothic110pro, FontWeight.Normal
    )
)

private val Bizudp = FontFamily(
    Font(R.font.bizudpgothicbold, FontWeight.Bold),
    Font(R.font.bizudpgothicregular, FontWeight.Normal)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Ydc,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.White
    ),

    titleLarge = TextStyle(
        fontFamily = Bizudp,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),

    bodyMedium = TextStyle(
        color = Color.White
    )


    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)