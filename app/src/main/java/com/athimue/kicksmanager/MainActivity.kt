package com.athimue.kicksmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.athimue.kicksmanager.navigation.MainNavigation
import com.athimue.kicksmanager.ui.theme.KicksManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bord à bord (obligatoire avec targetSdk 35+). La barre d'état passe au-dessus
        // de l'en-tête indigo : on force donc des icônes claires, en thème clair comme sombre.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        setContent {
            KicksManagerTheme {
                MainNavigation()
            }
        }
    }
}
