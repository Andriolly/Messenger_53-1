@file:Suppress("DEPRECATION")

package com.example.messenger53_1.screen.chat.Message

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.messenger53_1.DataMessanger
import com.example.messenger53_1.DataMessanger.chatName
import com.example.messenger53_1.R
import com.example.messenger53_1.model.Channel
import com.example.messenger53_1.model.Message
import com.example.messenger53_1.model.indivMessage
import com.example.messenger53_1.screen.chat.ChatViewModel
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyDark
import com.example.messenger53_1.ui.theme.bgItemCurUser
import com.example.messenger53_1.ui.theme.bgItemSendUser
import com.example.messenger53_1.ui.theme.brGreyLightBorder
import com.example.messenger53_1.ui.theme.txtGreyDark
import com.example.messenger53_1.ui.theme.txtGreyLightBorder
import com.example.messenger53_1.ui.theme.txtIcMainGrey
import com.example.messenger53_1.ui.theme.txtIcMainSelected
import com.example.messenger53_1.ui.theme.txtMainSelected
import com.example.messenger53_1.ui.theme.txtMainWhite
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MessageScreen(navController: NavController, channelId: String) {

    val viewModel: MessageViewModel = hiltViewModel()
    val chatViewModel: ChatViewModel = hiltViewModel() // Получаем ChatViewModel
    val messages = viewModel.messages.collectAsState()

    // Получаем имя чата
    val chatNameState = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        viewModel.listenForMessages(channelId)

        // Используем chatViewModel для получения имени
        chatViewModel.getChatName(channelId) { name ->
            chatNameState.value = name
            chatName = name // Обновляем глобальную переменную
        }

    }

    Log.d("message","chatNameState = ${chatNameState.value}   chatName = ${chatName}")


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding: PaddingValues -> //системный отступ для верхнего бара

            val chooserDialog = remember {
                mutableStateOf(false)
            }

            val cameraImageUri = remember {
                mutableStateOf<Uri?>(null)
            }

            val cameraImageLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture()
            ) { success ->
                if (success) {
                    cameraImageUri.value?.let {
                        // отправка изображения на сервер
                    }
                }
            }

            val imageLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
            }

            fun createImageUri(): Uri {

                val timestamp =
                    SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                val storageDir = ContextCompat.getExternalFilesDirs(
                    navController.context,
                    Environment.DIRECTORY_PICTURES
                ).first()

                return FileProvider.getUriForFile(
                    navController.context,
                    "${navController.context.packageName}.provider",
                    File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir).apply {
                        cameraImageUri.value = Uri.fromFile(this)
                    }
                )

            }

            val permissionLauncher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        cameraImageLauncher.launch(createImageUri())
                    }
                }

            ContentMessage(
                modifier = Modifier
                    .padding(innerPadding),
                messages, viewModel, channelId,
                navController as NavHostController,
                chatName = chatNameState.value, // Передаем имя
                chooserDialog = {
                    chooserDialog.value = true
                }

            )



            if (chooserDialog.value) {
                ContentSelectionDialog(
                    onCameraSelected = {
                        chooserDialog.value = false
                        if (navController.context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            cameraImageLauncher.launch(createImageUri())
                        } else {
                            //запросить разрешение
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    },
                    onGallerySelected = {
                        chooserDialog.value = false
                        imageLauncher.launch(
                            input = "image/*"
                        )
                    }
                )
            }

        }
    )


}


@Composable
fun ContentSelectionDialog(onCameraSelected: () -> Unit, onGallerySelected: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = onCameraSelected) {
                Text(
                    text = "Камера",
                    color = txtGreyDark
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onGallerySelected) {
                Text(
                    text = "Галерея",
                    color = txtGreyDark
                )
            }
        },
        title = { Text(text = "Выберите источник", color = txtMainSelected) },
        text = { Text(text = "Вы хотите выбрать камеру или галерею?", color = txtGreyLightBorder) }
    )

}

@Composable
fun ContentMessage(
    modifier: Modifier = Modifier,
    messages: State<List<Message>>,
    viewModel: MessageViewModel,
    channelId: String,
    navController: NavHostController,
    chatName: String = "",
    chooserDialog: () -> Unit,
) {

    Box(
        modifier = Modifier
            .background(bgGreyDark)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = bgGrey
                ),

            ) {
            ChatMessages(
                messages = messages.value,
                onSendMessage = { message ->
                    viewModel.sendMessage(channelID = channelId, message)

                },
                navController = navController,
                chatName = chatName, // Передаем имя
                onAttachClicked = {
                    chooserDialog()
                }
            )
        }
    }

}

@Composable
fun ChatMessages(
    messages: List<Message>,
    onSendMessage: (String) -> Unit,
    navController: NavHostController,
    onAttachClicked: () -> Unit,
    chatName: String,
) {
    val msg = remember {
        mutableStateOf("")
    }

    val hideKeyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    LaunchedEffect(messages) {
        if (messages.isNotEmpty()) {
            listState.scrollToItem(messages.size - 1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(bgGreyDark)
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {

                FloatingActionButton(
                    contentColor = txtMainWhite,
                    containerColor = Color.Transparent,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterStart
                        )
//                            .padding(16.dp)
                        .size(50.dp),
                    onClick = {
                        navController.popBackStack()
                        DataMessanger.chatName = ""
                    },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }

                Text(
                    text = DataMessanger.chatName,
                    fontSize = 25.sp,
                    color = txtMainWhite
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(top = 50.dp, bottom = 55.dp),
            state = listState,
        ) {

            items(messages) { message ->
                ItemMessageOnChat(message = message)
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
//                .padding(8.dp)
                .background(brGreyLightBorder),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = {
                    onAttachClicked()
                },
            ) {
                Icon(
                    tint = txtIcMainSelected,
                    imageVector = Icons.Filled.AttachFile,
                    contentDescription = "AttachFile"
                )
            }

            TextField(
                modifier = Modifier.weight(1f),
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
                value = msg.value,
                onValueChange = {
                    msg.value = it
                },
                label = {
                    Text(
                        text = "Введите сообщение"
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboardController?.hide()
                    }
                )
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = {
                    onSendMessage(msg.value)
                    msg.value = ""
                },
            ) {
                Icon(
                    tint = txtIcMainSelected,
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "sendMessage"
                )
            }

        }
    }

}

@Composable
fun ItemMessageOnChat(message: Message) {
    val isCurrentUser = message.senderId == Firebase.auth.currentUser?.uid
    val colorItem = if (isCurrentUser) {
        bgItemCurUser
    } else {
        bgItemSendUser
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp * 3 / 4

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        val alignment = if (!isCurrentUser) Alignment.CenterStart else Alignment.CenterEnd

        val date = Date(message.createdAT)
        val sdf = SimpleDateFormat("dd/MM/yy\nHH:mm ")
        val formattedDate = sdf.format(date)


        Row(
            modifier = Modifier
                .width(screenWidth)
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .align(alignment),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(50.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )


            Column(
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(start = 8.dp)
                    .background(colorItem, shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = message.senderName,
                    color = txtMainWhite,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 10.dp,
                        end = 6.dp,
                        bottom = 5.dp
                    ),
                    fontSize = 20.sp
                )
                Text(
                    text = message.message,
                    color = txtMainWhite,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    fontSize = 15.sp,
                    lineHeight = 15.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, bottom = 5.dp)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = formattedDate,
                        color = bgGreyDark,
                        lineHeight = 11.sp,
                        fontSize = 10.sp
                    )
                }
            }


        }

    }


}