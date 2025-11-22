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
import androidx.navigation.NavHostController
import com.example.messenger53_1.bottomNavigation.BottomItem
import com.example.messenger53_1.screen.chat.ChatScreen
import com.example.messenger53_1.screen.contacts.ContactsScreen
import com.example.messenger53_1.screen.profile.ProfileScreen
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.icMainGrey
import com.example.messenger53_1.ui.theme.icMainSelected
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected


@Composable
fun AppScreen(navController: NavHostController,modifier: Modifier = Modifier) {
    val navItems = listOf(
        BottomItem(title = "Контакты", iconId = Icons.Default.AccountCircle),
        BottomItem(title = "Чаты", iconId = Icons.Default.Home, badgeCount = 5),
        BottomItem(title = "Профиль", iconId = Icons.Default.Info)
    )

    var selectedIndexed by remember {
        mutableIntStateOf(1)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = bgGreyDark,
                tonalElevation = 0.dp,

                ) {
                navItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent), //удаление рамки вокруг выбранного элемента
                        selected = selectedIndexed == index,
                        onClick = {
                            selectedIndexed = index
                        },
                        icon = {
                            BadgedBox(
                                badge =
                                    {
                                        if (navItem.badgeCount != 0)
                                            Badge {
                                                Text(text = navItem.badgeCount.toString())
                                            }
                                    }
                            ) {
                                if (selectedIndexed == index) {
                                    Icon(
                                        imageVector = navItem.iconId,
                                        contentDescription = "",
                                        tint = icMainSelected
                                    )
                                } else {
                                    Icon(
                                        imageVector = navItem.iconId,
                                        contentDescription = "",
                                        tint = icMainGrey
                                    )
                                }
                            }

                        },
                        label = {
                            if (selectedIndexed == index) {
                                Text(
                                    color = txtIcMainSelected, text = navItem.title
                                )
                            } else {
                                Text(
                                    color = txtIcMainGrey, text = navItem.title
                                )
                            }

                        },

                        )
                }
            }
        },
        content = { innerPadding: PaddingValues -> //системный отступ для верхнего бара

            ContentScreen(modifier = Modifier
                .padding(innerPadding), selectedIndexed, navController)

        }
    )
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndexed: Int,
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .background(bgGreyDark)
    ){
        when (selectedIndexed) {
            0 -> ContactsScreen(modifier = modifier)
            1 -> ChatScreen(modifier = modifier)
            2 -> ProfileScreen(modifier = modifier, navController)
        }
    }

}



