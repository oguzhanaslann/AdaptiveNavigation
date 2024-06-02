package com.oguzhanaslann.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainScreenNavigationHost(
    modifier : Modifier = Modifier,
    navHost: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHost,
        startDestination = MainScreen.AccountScreen.route
    ) {
        composable(screen = MainScreen.AccountScreen) {
            AccountScreen(onDetailClicked = {
                navHost.navigate(MainScreen.AccountDetailScreen.route)
            })
        }

        composable(screen = MainScreen.AccountDetailScreen) {
            AccountScreenDetail()
        }

        composable(screen = MainScreen.SearchScreen) {
            SearchScreen(onDetailClicked = {
                navHost.navigate(MainScreen.SearchDetailScreen.route)
            })
        }

        composable(screen = MainScreen.SearchDetailScreen) {
            SearchScreenDetail()
        }
    }
}