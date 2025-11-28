package com.example.messenger53_1.screen.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.messenger53_1.ui.theme.bgGrey

@Composable
fun ContactsScreen(modifier: Modifier = Modifier, navController: NavHostController) {
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