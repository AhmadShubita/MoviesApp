package com.ahmadshubita.moviesapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ahmadshubita.moviesapp.ui.bottombar.MoviesDestination
import com.ahmadshubita.moviesapp.ui.bottombar.allItemsGraph
import com.ahmadshubita.moviesapp.ui.bottombar.detailsGraph
import com.ahmadshubita.moviesapp.ui.bottombar.moviesGraph
import com.ahmadshubita.moviesapp.ui.bottombar.peopleGraph
import com.ahmadshubita.moviesapp.ui.bottombar.tvGraph

@Composable
fun MainNavHost(
        navController: NavHostController = rememberNavController(),
        startDestination: String = MoviesDestination.route,
        modifier: Modifier = Modifier,
        isBottomNavVisible: MutableState<Boolean>,
) {
    NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
    ) {
        // Define the Graphs here and you can define nested graphs also.
        moviesGraph(navController = navController, isBottomNavVisible = isBottomNavVisible, navigateToDetails = {})
        tvGraph(navController = navController, isBottomNavVisible = isBottomNavVisible, navigateToDetails = {})
        peopleGraph(navController = navController, isBottomNavVisible = isBottomNavVisible, navigateToDetails = {})
        allItemsGraph(navController = navController, isBottomNavVisible = isBottomNavVisible, navigateToDetails = {})
        detailsGraph(navController = navController, isBottomNavVisible = isBottomNavVisible)
    }
}