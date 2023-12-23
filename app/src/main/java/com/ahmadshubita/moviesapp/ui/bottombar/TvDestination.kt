package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination

object TvDestination : MainNavDestination {
    override val route: String
        get() = "tv_route"
    override val destination: String
        get() = "tv_destinations"

}

fun NavGraphBuilder.tvGraph(navController: NavController, isBottomNavVisible: MutableState<Boolean>, navigateToDetails: () -> Unit){
    composable(route = TvDestination.route){
        LaunchedEffect(key1 = null ){
            isBottomNavVisible.value = true
        }
        // TODO adding the route for movies screen and passing require data.
    }
}