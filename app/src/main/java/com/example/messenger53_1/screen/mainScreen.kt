package com.example.messenger53_1.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messenger53_1.bottomNavigation.BottomItem
import com.example.messenger53_1.screen.chat.ChatScreen
import com.example.messenger53_1.screen.contacts.ContactsScreen
import com.example.messenger53_1.screen.profile.ProfileScreen
import com.example.messenger53_1.screen.Login.signIn.SignInScreen
import com.example.messenger53_1.screen.Login.signUp.SignUpScreen
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.icMainGrey
import com.example.messenger53_1.ui.theme.icMainSelected
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected
import com.google.firebase.auth.FirebaseAuth

// ДОДЕЛАТЬ!!!
// isError в signupscreen


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val start = if (currentUser != null) "app" else "login"

    NavHost(navController = navController, startDestination = start) {
        composable(route = "login") {
            SignInScreen(navController)
        }
        composable(route = "signup") {
            SignUpScreen(navController)
        }
        composable(route = "app") {
            AppScreen(navController)
        }
    }
}




