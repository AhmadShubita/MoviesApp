package com.ahmadshubita.moviesapp.ui.core.navigation

interface MainNavDestination {

    /**
     * Describes a route for a destination.
     * The route, a string representing the path to a composable, acts as an implicit deep link.
     * Each destination should have a unique route.
     */
    val route: String


    /**
     * Define destination ID.
     * Especially when using nested graphs through the navigation DLS.
     * It helps differentiate the route of a specific destination from the route of the entire nested graph it belongs to.
     */
    val destination: String
}