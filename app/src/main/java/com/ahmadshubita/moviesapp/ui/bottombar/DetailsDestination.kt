package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination
import com.ahmadshubita.moviesapp.ui.movies.details.DetailsScreen
import com.ahmadshubita.moviesapp.ui.movies.details.viewmodel.DetailsScreenArgs

object DetailsDestination : MainNavDestination {
    override val route: String
        get() = "details_route"
    override val destination: String
        get() = "details_destinations"
}

fun NavGraphBuilder.detailsGraph(
    navController: NavController,
    isBottomNavVisible: MutableState<Boolean>
) {
    composable(
        route = "${DetailsDestination.route}/{${DetailsScreenArgs.DETAILS_TYPE}},{${DetailsScreenArgs.ID}}",
        arguments = listOf(
            navArgument(DetailsScreenArgs.DETAILS_TYPE) { defaultValue = DetailsType.MOVIE_DETAILS },
            navArgument(DetailsScreenArgs.ID) {}
        ),
    ) {
        LaunchedEffect(key1 = null) {
            isBottomNavVisible.value = false
        }
        DetailsScreen(navController = navController)
    }
}

fun NavController.navigateToDetailsScreen(
    detailsType: DetailsType, id: String
) {
    navigate("${DetailsDestination.route}/$detailsType, ${id}")
}

enum class DetailsType {
    TV_DETAILS,
    MOVIE_DETAILS
}

