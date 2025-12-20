package com.example.messenger53_1.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.messenger53_1.DataMessanger.chatName
import com.example.messenger53_1.firm.FirmOutlineTextField
import com.example.messenger53_1.model.Channel
import com.example.messenger53_1.model.indivMessage
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.bgItemCurUser
import com.example.messenger53_1.ui.theme.brGreyLightBorder
import com.example.messenger53_1.ui.theme.btnMainOrange
import com.example.messenger53_1.ui.theme.txtGreyDark
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected
import com.example.messenger53_1.ui.theme.txtMainSelected
import com.example.messenger53_1.ui.theme.txtMainWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel = hiltViewModel<ChatViewModel>()
    val channels = viewModel.channels.collectAsState()
    val individualMessages = viewModel.individualMessages.collectAsState()
    val addChannel = remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()

    val isActiveChannel = remember {
        mutableStateOf(true)
    }


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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .height(30.dp)
                        .background(bgGreyDark)
                        .padding(top = 10.dp, bottom = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ЧАТЫ",
                        fontSize = 35.sp,
                        color = txtMainWhite,
                        fontWeight = W700
                    )

                }
            }
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
////                        .height(50.dp)
//                        .background(bgGreyDark)
//                        .padding(bottom = 16.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    var index by remember {
//                        mutableStateOf("")
//                    }
//                    FirmOutlineTextField(
//                        label = "Поиск",
//                        value = { index = it },
//                        search = true
//                    )
//                }
//            }
            item {
                HorizontalDivider(
                    color = brGreyLightBorder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    val colorSelected = txtMainSelected
                    val colorUnSelected = bgItemCurUser


                    if (isActiveChannel.value == true) {
                        TextButton(onClick = { isActiveChannel.value = true }) {
                            Text(
                                fontWeight = W700,
                                color = colorSelected,
                                text = "Каналы",
                                fontSize = 25.sp
                            )
                        }
                        TextButton(onClick = { isActiveChannel.value = false }) {
                            Text(
                                fontWeight = W700,
                                color = colorUnSelected,
                                text = "Личные",
                                fontSize = 25.sp
                            )
                        }
                    } else {
                        TextButton(onClick = { isActiveChannel.value = true }) {
                            Text(
                                fontWeight = W700,
                                color = colorUnSelected,
                                text = "Каналы",
                                fontSize = 25.sp
                            )
                        }
                        TextButton(onClick = { isActiveChannel.value = false }) {
                            Text(
                                fontWeight = W700,
                                color = colorSelected,
                                text = "Личные",
                                fontSize = 25.sp
                            )
                        }
                    }

                }
            }
            item {
                HorizontalDivider(
                    color = brGreyLightBorder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
            if (isActiveChannel.value) {
                items(channels.value) { channel ->
                    Column {
                        ItemChatChannel(channel, onClick = {
                            navController.navigate(
                                "chat/${channel.id}"
                            )
                            chatName = channel.name
                        })
                    }
                }
            } else {
                items(individualMessages.value) { inMessage ->
                    Column {
                        ItemChatIndMassage(inMessage, onClick = {
                            navController.navigate(
                                "chat/${inMessage.id}"
                            )
                            chatName = inMessage.name
                        })
                    }
                }
            }


        }
        if (isActiveChannel.value) {
            FloatingActionButton(
                contentColor = txtMainWhite,
                containerColor = txtIcMainSelected,
                shape = CircleShape,
                modifier = modifier
                    .align(
                        alignment = Alignment.BottomEnd
                    )
                    .padding(16.dp)
                    .size(50.dp),
                onClick = {
                    addChannel.value = true
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = ""
                )
            }
        }
    }

    if (addChannel.value) {
        ModalBottomSheet(
            containerColor = bgGreyDark,
            modifier = Modifier
                .background(txtMainWhite),
            onDismissRequest = { addChannel.value = false },
            sheetState = sheetState
        ) {
            AddChannelDialog {
                viewModel.addChannel(it)
                addChannel.value = false
            }
        }
    }

}


@Composable
fun ItemChatIndMassage(indivMessage: indivMessage, onClick: () -> Unit) {

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
            Text(
                text = indivMessage.name[0].uppercase(),
                color = txtMainWhite,
                fontSize = 30.sp,
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = indivMessage.name,
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

@Composable
fun ItemChatChannel(channel: Channel, onClick: () -> Unit) {

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
            Text(
                text = channel.name[0].uppercase(),
                color = txtMainWhite,
                fontSize = 30.sp,
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = channel.name,
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

@Composable
fun AddChannelDialog(onAddChannel: (String) -> Unit) {
    val channelName = remember {
        mutableStateOf("")
    }
    Column(

        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Добавить канал",
            color = txtIcMainGrey,
            fontSize = 25.sp
        )
        Spacer(
            modifier = Modifier.padding(8.dp)
        )
        TextField(
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = txtIcMainGrey,
                unfocusedContainerColor = brGreyLightBorder,
                focusedTextColor = txtMainWhite,
                focusedContainerColor = brGreyLightBorder,
                focusedLabelColor = txtIcMainSelected,
                unfocusedLabelColor = txtIcMainSelected,
                cursorColor = txtIcMainSelected,
                focusedIndicatorColor = txtIcMainSelected,

                ),
            value = channelName.value,
            onValueChange = {
                channelName.value = it
            },
            label = {
                Text(
                    text = "Название канала"
                )
            },
            singleLine = true,
        )

        Spacer(
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                onAddChannel(channelName.value)

            },
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 5.dp),
            colors = btnMainOrange
        ) {
            Text(text = "Добавить")
        }

    }
}

