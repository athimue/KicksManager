package com.athimue.kicksmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.athimue.kicksmanager.navigation.MainNavigation
import com.athimue.kicksmanager.ui.theme.KicksManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bord à bord (obligatoire avec targetSdk 35+) : barres système transparentes,
        // icônes claires/sombres adaptées automatiquement au thème.
        enableEdgeToEdge()
        setContent {
            KicksManagerTheme {
                MainNavigation()
            }
        }
    }
}
