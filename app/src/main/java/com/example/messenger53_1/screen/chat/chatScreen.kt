package com.example.messenger53_1.screen.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messenger53_1.R
import com.example.messenger53_1.screen.profile.HeaderProfile
import com.example.messenger53_1.screen.profile.InfoProfile
import com.example.messenger53_1.screen.profile.MenuApplication
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.btnMainOrange
import com.example.messenger53_1.ui.theme.txtIcMainSelected

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
//fun ChatScreen(modifier: Modifier = Modifier) {
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(
                color = bgGrey
            ),

        ) {

        Text(text = "Чаты")

    }

}