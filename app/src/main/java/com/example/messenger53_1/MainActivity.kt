package com.example.messenger53_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.messenger53_1.screen.MainScreen
import com.example.messenger53_1.ui.theme.Messenger531Theme
import com.example.messenger53_1.ui.theme.bgGrey
import com.example.messenger53_1.ui.theme.bgGreyStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Messenger531Theme{
                MainScreen()
            }
        }
    }
}