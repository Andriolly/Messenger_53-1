package com.example.messenger53_1.firm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.messenger53_1.ui.theme.icMainSelected
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtMainSelected
import com.example.messenger53_1.ui.theme.txtMainWhite

@Composable
fun FirmOutlineTextField(
    /**
     * Название вводимого поля
     * */
    label: String,
    /**
     * Значение вводимого поля
     * */
    value: (String) -> Unit,
    /**
     * Верхний отступ (равен 0)
     * */
    paddingTop: Dp = 0.dp,
    /**
     * Нижний отступ (равен 0)
     * */
    paddingBottom: Dp = 0.dp,
    /**
     * Пароль или нет
     * */
    password: Boolean = false,
    /**
     * Ошибка или нет
     * */
    error: Boolean = false,
    /**
     * Поиск или нет
     * */
    search: Boolean = false,


) {

    var text by remember { mutableStateOf("") }


    if (password) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                errorTextColor = Color.Red,
                focusedTextColor = txtMainWhite,
                focusedBorderColor = icMainSelected,
                unfocusedBorderColor = txtIcMainGrey,
                unfocusedTextColor = txtIcMainGrey
            ),
            modifier = Modifier
                .padding(top = paddingTop, bottom = paddingBottom),
            label = {
                Text(
                    color = txtMainWhite,
                    text = label
                )
            },
            value = text,
            onValueChange = {
                text = it
                value(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = error
            )
    }
    else if (search){
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = txtMainWhite,
                focusedBorderColor = icMainSelected,
                unfocusedBorderColor = txtIcMainGrey,
                unfocusedTextColor = txtIcMainGrey
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingTop, bottom = paddingBottom)
                .padding(horizontal = 16.dp),
            label = {
                Text(
                    color = txtMainWhite,
                    text = label
                )
            },
            value = text,
            onValueChange = {
                text = it
                value(it)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = txtMainWhite
                )
            },
            singleLine = true

        )
    }
    else {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = txtMainWhite,
                focusedBorderColor = icMainSelected,
                unfocusedBorderColor = txtIcMainGrey,
                unfocusedTextColor = txtIcMainGrey
            ),
            modifier = Modifier
                .padding(top = paddingTop, bottom = paddingBottom),
            label = {
                Text(
                    color = txtMainWhite,
                    text = label
                )
            },
            value = text,
            onValueChange = {
                text = it
                value(it)
            },
            singleLine = true
        )
    }


}