package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahmadshubita.moviesapp.ui.all.AllItemsScreen
import com.ahmadshubita.moviesapp.ui.all.viewmodel.AllItemsScreenArgs
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination

object AllItemsDestination : MainNavDestination {
    override val route: String
        get() = "all_item_route"
    override val destination: String
        get() = "all_item_destinations"

}

fun NavGraphBuilder.allItemsGraph(
    navController: NavController, isBottomNavVisible: MutableState<Boolean>
) {
    composable(route = "${AllItemsDestination.route}/{${AllItemsScreenArgs.IS_POPULAR}}",
        arguments = listOf(navArgument(AllItemsScreenArgs.IS_POPULAR) { defaultValue = false })
    ) {
        LaunchedEffect(key1 = null) {
            isBottomNavVisible.value = false
        }
        AllItemsScreen(navController = navController)
    }
}

fun NavController.navigateAllItemsScreen(
    isPopularItemsScreen: Boolean
) {
    navigate("${AllItemsDestination.route}/$isPopularItemsScreen")
}

