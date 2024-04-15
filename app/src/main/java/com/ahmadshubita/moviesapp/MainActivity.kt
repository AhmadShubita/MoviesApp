package com.ahmadshubita.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.dataStore
import com.ahmadshubita.moviesapp.data.local.MainDataStore
import com.ahmadshubita.moviesapp.ui.MoviesApp
import com.ahmadshubita.moviesapp.ui.movies.viewmodel.MoviesScreenState
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: MainDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var darkThemeState by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true){
                dataStore.getDarkThemePrefs().collect{
                    darkThemeState = it
                }
            }
            MoviesApp(darkThemeState)
        }
    }
}
