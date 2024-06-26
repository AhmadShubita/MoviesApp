package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahmadshubita.moviesapp.ui.people.PeopleScreen
import com.ahmadshubita.moviesapp.ui.core.navigation.MainNavDestination

object PeoplesDestination : MainNavDestination {
    override val route: String
        get() = "people_route"
    override val destination: String
        get() = "people_destinations"

}

fun NavGraphBuilder.peopleGraph(navController: NavController, isBottomNavVisible: MutableState<Boolean>){
    composable(route = PeoplesDestination.route){
        LaunchedEffect(key1 = null ){
            isBottomNavVisible.value = true
        }
        PeopleScreen(navController = navController)
    }
}