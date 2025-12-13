package com.example.messenger53_1.screen.Login.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.messenger53_1.firm.FirmOutlineTextField
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.btnMainOrange
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected

@Composable
fun SignUpScreen(navController: NavHostController) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {
        when (uiState.value) {
            is SignUpState.Success -> {
                navController.navigate(route = "app")
            }

            is SignUpState.Error -> {
                Toast.makeText(context, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
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
        Spacer(
            modifier = Modifier
                .padding(top = 150.dp)
        )


        Text(

            color = txtIcMainSelected,
            text = "Давайте начнем!",
            fontWeight = W700,
            fontSize = 22.sp
        )
        Spacer(
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Text(
            color = txtIcMainSelected,
            text = "Зарегистрируйте свой новый аккаунт",
            fontWeight = W700,
            fontSize = 20.sp
        )

        var isError by remember {
            mutableStateOf(false)
        }
        isError = password != confirmPassword

        Spacer(
            modifier = Modifier
                .padding(top = 100.dp)
        )

        FirmOutlineTextField(
            label = "Имя пользователя",
            value = { name = it },
            paddingTop = 10.dp
        )
        FirmOutlineTextField(
            label = "Электронная почта",
            value = { email = it },
            paddingTop = 10.dp
        )
        FirmOutlineTextField(
            label = "Пароль",
            value = { password = it },
            paddingTop = 10.dp,
            password = true
        )
        FirmOutlineTextField(
            label = "Подтвердите пароль",
            value = { confirmPassword = it },
            paddingTop = 10.dp,
            password = true,
            error = isError
        )

        if (uiState.value == SignUpState.Loading) {
            CircularProgressIndicator(
                color = txtIcMainSelected,
                modifier = Modifier
                    .padding(top = 40.dp)
            )
        } else {
            Button(
                modifier = Modifier
                    .padding(top = 40.dp),
                colors = btnMainOrange,
                onClick = {
                    viewModel.signUp(
                        name = name,
                        email = email,
                        password = password
                    )
                },
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                    fontWeight = W700,
                    fontSize = 18.sp,
                    text = "Регистрация"
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                color = txtIcMainGrey,
                text = "У вас есть аккаунт?"
            )
            TextButton(onClick = { navController.popBackStack() }) {
                Text(fontWeight = W700, color = txtIcMainGrey, text = "Авторизация")
            }
        }
    }
}