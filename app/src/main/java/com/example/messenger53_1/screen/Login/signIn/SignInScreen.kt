package com.example.messenger53_1.screen.Login.signIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.messenger53_1.R
import com.example.messenger53_1.firm.FirmOutlineTextField
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.btnMainOrange
import com.example.messenger53_1.ui.theme.icMainSelected
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected
import com.example.messenger53_1.ui.theme.txtMainSelected

@Composable
fun SignInScreen(navController: NavHostController) {


    val viewModel: SignInViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()


    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SignInState.Success -> {
                navController.navigate(route = "app")
            }

            is SignInState.Error -> {
                Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
            }
            else -> {}

        }

    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGreyDark),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .padding(top = 150.dp, bottom = 30.dp)
                .size(150.dp),
            painter = painterResource(R.drawable.logo), contentDescription = null,
        )

        Text(
            color = txtIcMainSelected,
            text = "Добро пожаловать!",
            fontWeight = W700,
            fontSize = 22.sp
        )
        Text(
            color = txtIcMainSelected,
            text = "Войдите в свой аккаунт",
            fontWeight = W500,
            fontSize = 22.sp
        )

        FirmOutlineTextField(
            label = "Электронная почта",
            value = {email = it},
            paddingTop = 20.dp,
            paddingBottom = 10.dp,
        )

        FirmOutlineTextField(
            label = "Пароль",
            value = {password = it},
            paddingBottom = 10.dp,
            password = true
        )

//        OutlinedTextField(
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor= txtMainSelected,
//                focusedBorderColor = icMainSelected,
//                unfocusedBorderColor = txtIcMainGrey,
//                unfocusedTextColor = txtIcMainGrey
//            ),
//            modifier = Modifier
//                .padding(top = 20.dp, bottom = 10.dp),
//            label = {
//                Text(text = "Электронная почта")
//            },
//            value = email,
//            onValueChange = { email = it })
//        OutlinedTextField(
//            label = {
//                Text(text = "Пароль")
//            },
//            value = password,
//            visualTransformation = PasswordVisualTransformation(),
//            onValueChange = { password = it }
//        )

        if (uiState.value == SignInState.Loading) {
            CircularProgressIndicator( modifier = Modifier
                .padding(top = 40.dp))
        } else {
            Button(
                modifier = Modifier
                    .padding(top = 40.dp),
                colors = btnMainOrange,
                onClick = { viewModel.signIn(email = email, password = password) },
                enabled = email.isNotEmpty() && password.isNotEmpty() && (uiState.value == SignInState.Nothing || uiState.value == SignInState.Error)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                    fontWeight = W700,
                    fontSize = 18.sp,
                    text = "Авторизация"
                )
            }
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                color = txtIcMainGrey,
                text = "У вас нет учетной записи?"
            )
            TextButton(onClick = { navController.navigate(route = "signup") }) {
                Text(fontWeight = W700, color = txtIcMainGrey, text = "Регистрация")
            }
        }


    }
}