package com.example.messenger53_1.screen.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.messenger53_1.screen.profile.HeaderProfile
import com.example.messenger53_1.screen.profile.InfoProfile
import com.example.messenger53_1.screen.profile.MenuApplication
import com.example.messenger53_1.ui.theme.bgGrey

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(
                color = bgGrey
            ),

        ) {
        Text(text = "Контакты")

    }

}