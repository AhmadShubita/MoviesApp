package com.ahmadshubita.moviesapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ahmadshubita.moviesapp.R

val Helvetica = FontFamily(
    Font(R.font.helvetica, weight = FontWeight.Bold),
    Font(R.font.helvetica_light, weight = FontWeight.Light)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp

    )
)