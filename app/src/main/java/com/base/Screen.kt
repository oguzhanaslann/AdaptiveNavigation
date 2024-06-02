package com.base

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable


abstract class Screen(val route: String) {
    companion object {
        fun route(routeBase: String, vararg screenArgs: String): String {
            var route = routeBase
            screenArgs.forEach { route = route.plus("/{$it}") }
            return route
        }
    }
}

fun NavHostController.navigate(screen: Screen) {
    navigate(screen.route)
}


fun NavHostController.navigate(screen: Screen, builder: NavOptionsBuilder.() -> Unit) {
    navigate(screen.route, builder)
}


fun NavGraphBuilder.composable(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable  AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = screen.route,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )
}