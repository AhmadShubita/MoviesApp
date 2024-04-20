package com.ahmadshubita.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ahmadshubita.moviesapp.data.local.MainDataStore
import com.ahmadshubita.moviesapp.ui.MoviesApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: MainDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            var darkThemeState by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                dataStore.getDarkThemePrefs().collect {
                    darkThemeState = it
                }
            }

            enableEdgeToEdge(
                statusBarStyle = if (darkThemeState) {
                    SystemBarStyle.dark(
                        android.graphics.Color.TRANSPARENT,
                    )
                } else {
                    SystemBarStyle.light(
                        android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
                    )
                }
            )
            MoviesApp(darkThemeState)
        }
    }
}
