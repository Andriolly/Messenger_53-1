package com.example.messenger53_1.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messenger53_1.R
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.brGreyDarkBorder
import com.example.messenger53_1.ui.theme.txtMainSelected
import com.example.messenger53_1.ui.theme.txtMainWhite
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

//Создание страницы "Профиль"
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    Column(
        modifier= modifier
            .fillMaxSize()
            .background(
                color = bgGrey
            ),

        ) {

        HeaderProfile(navController)

        InfoProfile()

        MenuApplication()

    }

}

@Composable
fun HeaderProfile(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = bgGreyDark
            )
            .height(175.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar_cat),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(3.dp, CircleShape)
                        .clip(CircleShape)
                        .clickable(
                            onClick = {},
                            indication = ripple(),
                            interactionSource = remember { MutableInteractionSource() }
                        ),

                    )
                Spacer(
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "Котов Кот Котович",
                    color = txtMainWhite,
                    fontSize = 20.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(R.drawable.exit_profile),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
                    .clickable(
                        onClick = { Firebase.auth.signOut()
                            navController.navigate(route = "login")},
                        indication = ripple(),
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    ,

            )
        }


    }

}

@Composable
fun InfoProfile() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Аккаунт",
                color = txtMainWhite,
                fontSize = 20.sp,
                fontWeight = W700
            )

            Image(
                painter = painterResource(R.drawable.edit),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        onClick = {},
                        indication = ripple(),
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, color = brGreyDarkBorder)
                .background(
                    color = bgGreyDark
                )
                .padding(vertical = 10.dp)
        ) {
            ProfileRowAccounts(txtHeader = "Номер телефона", txtData = "+7(999)999-99-99")
            ProfileRowAccounts(txtHeader = "Имя пользователя", txtData = "@Koterinio")
        }
    }
}

@Composable
fun ProfileRowAccounts(txtHeader: String, txtData: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = txtHeader,
            color = txtMainWhite,
            fontSize = 16.sp,
        )
        Text(
            text = txtData,
            color = txtMainSelected,
            fontSize = 16.sp,
            fontWeight = W500
        )
    }
}

@Composable
fun MenuApplication() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Настройки",
                color = txtMainWhite,
                fontSize = 20.sp,
                fontWeight = W700
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, color = brGreyDarkBorder)
                .background(
                    color = bgGreyDark
                )
        ) {
            ProfileRowSettings("Чаты", R.drawable.profile_message)
            ProfileRowSettings("Уведомления", R.drawable.profile_notification)
            ProfileRowSettings("Конфиденциальность", R.drawable.profile_confidance)
            ProfileRowSettings("О приложении", R.drawable.profile_info)
        }
    }
}

@Composable
fun ProfileRowSettings(text: String, idPainterResource: Int, paddingHorizontalRow: Dp = 25.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {},
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = paddingHorizontalRow, vertical = 12.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idPainterResource),
            contentDescription = "",
            modifier = Modifier
                .size(35.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = text,
            color = txtMainWhite,
            fontSize = 18.sp,
        )
    }
}