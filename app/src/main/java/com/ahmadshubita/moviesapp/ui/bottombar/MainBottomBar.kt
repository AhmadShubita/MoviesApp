package com.ahmadshubita.moviesapp.ui.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme


@Composable fun MoviesBottomBar(
    onNavigateToDestination: (MainDestination) -> Unit,
    currentDestination: NavDestination?
) {

    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        NavigationBar(
            tonalElevation = 0.dp,
        ) {

            bottomBarItems.forEach { destination ->
                val selectedItem =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                NavigationBarItem(modifier = Modifier.fillMaxWidth().height(56.dp), selected = selectedItem, onClick = { onNavigateToDestination(destination)}, icon = {
                    Icon(
                        if (selectedItem) {
                            painterResource(destination.selectedIcon)
                        } else {
                            painterResource(destination.unselectedIcon)
                        }, contentDescription = null)
                }, label = { Text(stringResource(destination.bottomStringRes))})
            }
        }
    }
}

data class MainDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val bottomStringRes: Int
)

val bottomBarItems = listOf(
    MainDestination(
        route = MoviesDestination.route,
        selectedIcon = R.drawable.ic_movies_seleced,
        unselectedIcon = R.drawable.ic_movies_unselected,
        bottomStringRes = R.string.movies
    ),
    MainDestination(
        route = TvDestination.route,
        selectedIcon = R.drawable.ic_tv_selected,
        unselectedIcon = R.drawable.ic_tv_unselected,
        bottomStringRes = R.string.tv
    ),
    MainDestination(
        route = PeoplesDestination.route,
        selectedIcon = R.drawable.ic_people_selected,
        unselectedIcon = R.drawable.ic_people_unselected,
        bottomStringRes = R.string.people
    )
)


class MainBottomNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: MainDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DoseBottomBarPreview() {
     MoviesAppTheme{
         val navController = rememberNavController()
         val doseTopLevelNavigation = remember(navController) {
             MainBottomNavigation(navController)
         }

         val navBackStackEntry by navController.currentBackStackEntryAsState()
         val currentDestination = navBackStackEntry?.destination

//         MoviesBottomBar (currentDestination)
    }
}

