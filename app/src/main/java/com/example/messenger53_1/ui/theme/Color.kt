package com.example.messenger53_1.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val icMainGrey = Color(0xFFC7C7C7)

val icMainSelected = Color(0xFFF56E0F)

val btnMainOrange: ButtonColors
    @Composable
    get() = buttonColors(
        containerColor = Color(0xFFF56E0F),
        contentColor = Color.White,
        disabledContainerColor = Color(0xFF434343),
        disabledContentColor = Color(0xFFF9F9F9))

val bgGrey = Color(0xFF434343)

val bgGreyStatusBar = 0xFF6650a4.toInt()

val bgGreyDark = Color(0xFF373737)

val brGreyDarkBorder = Color(0xFF1E1E1E)

val txtIcMainGrey = Color(0xFFC7C7C7)

val txtIcMainSelected = Color(0xFFF56E0F)

val txtMainWhite= Color(0xFFF9F9F9)

val txtMainSelected = Color(0xFFF56E0F)
