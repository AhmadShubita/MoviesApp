package com.ahmadshubita.moviesapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmadshubita.moviesapp.ui.bottombar.MainBottomBar
import com.ahmadshubita.moviesapp.ui.bottombar.MainBottomNavigation
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavHost
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesApp(isDarkTheme: Boolean) {
    MoviesAppTheme(isDarkTheme = isDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val bottomBarVisibility = rememberSaveable { (mutableStateOf(true)) }

            val navController = rememberNavController()
            val mainBottomNavigation = remember(navController) {
                MainBottomNavigation(navController)
            }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.background,
                bottomBar = {
                    AnimatedVisibility(
                            visible = bottomBarVisibility.value,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it }),
                        content = {
                            MainBottomBar(
                                onNavigateToDestination = mainBottomNavigation::navigateTo,
                                currentDestination = currentDestination,
                            )
                        })
                }) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {
                    MainNavHost(
                        isBottomNavVisible = bottomBarVisibility,
                        navController = navController,
                        modifier = Modifier
                            .padding(padding)
                            .consumeWindowInsets(padding)
                            .zIndex(1f)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesAppTheme {
        MoviesApp(isDarkTheme = false)
    }
}