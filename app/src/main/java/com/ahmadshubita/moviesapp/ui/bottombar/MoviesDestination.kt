package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination
import com.ahmadshubita.moviesapp.ui.movies.MoviesScreen

object MoviesDestination : MainNavDestination {
    override val route: String
        get() = "movies_route"
    override val destination: String
        get() = "movies_destinations"
}

fun NavGraphBuilder.moviesGraph(navController: NavController, isBottomNavVisible: MutableState<Boolean>, navigateToDetails: () -> Unit){
    composable(route = MoviesDestination.route){
        LaunchedEffect(key1 = null ){
            isBottomNavVisible.value = true
        }
        // TODO adding the route for movies screen and passing require data.
        MoviesScreen(navController = navController)
    }
}