package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination
import com.ahmadshubita.moviesapp.ui.movies.details.MovieDetailsScreen

object MovieDetailsDestination : MainNavDestination {
    override val route: String
        get() = "movie_details_route"
    override val destination: String
        get() = "movie_details_destinations"
}

fun NavGraphBuilder.movieDetailsGraph(
    navController: NavController,
    isBottomNavVisible: MutableState<Boolean>
) {
    composable(
        route = MovieDetailsDestination.route,
    ) {
        LaunchedEffect(key1 = null) {
            isBottomNavVisible.value = false
        }
        MovieDetailsScreen(navController = navController)
    }
}

fun NavController.navigateMovieDetailsScreen(
) {
    navigate(MovieDetailsDestination.route)
}

