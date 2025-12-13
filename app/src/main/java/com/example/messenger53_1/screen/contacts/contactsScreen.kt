package com.example.messenger53_1.screen.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.messenger53_1.DataMessanger.chatName
import com.example.messenger53_1.R
import com.example.messenger53_1.firm.FirmOutlineTextField
import com.example.messenger53_1.model.Channel
import com.example.messenger53_1.model.UserData
import com.example.messenger53_1.screen.chat.ChatViewModel
import com.example.messenger53_1.screen.chat.ItemChat
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.brGreyLightBorder
import com.example.messenger53_1.ui.theme.txtIcMainSelected
import com.example.messenger53_1.ui.theme.txtMainWhite

@Composable
fun ContactsScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val viewModel = hiltViewModel<ContactsScreenViewModel>()
    val users = viewModel.users.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()

    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = bgGrey
                ),

            ) {
            item {
                HorizontalDivider(
                    color = brGreyLightBorder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
            items(users.value) { users ->
                Column {
                    ItemUser(users, onClick = {
                    })
                }
            }

        }

    }
}


@Composable
fun ItemUser(users: UserData, onClick: () -> Unit) {

//    val date = Date(channel.createdAT)
//    val sdf = SimpleDateFormat("dd/MM/yy HH:mm ")
//    val formattedDate = sdf.format(date)

    Row(
        modifier = Modifier
            .background(bgGreyDark)
            .fillMaxWidth()
            .height(75.dp)
            .padding(horizontal = 8.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(txtIcMainSelected)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
//                    .padding(top = 150.dp, bottom = 30.dp)
                    .size(30.dp),
                painter = painterResource(R.drawable.logo), contentDescription = null,
            )
//            Text(
//                text = users.name[0].uppercase(),
//                color = txtMainWhite,
//                fontSize = 30.sp,
//            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = users.name,
                fontSize = 20.sp,
                color = txtMainWhite
            )
//            Text(
//                text = formattedDate,
//                fontSize = 16.sp,
//                color = txtMainWhite
//            )
        }
    }
    HorizontalDivider(
        color = brGreyLightBorder,
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    )
}