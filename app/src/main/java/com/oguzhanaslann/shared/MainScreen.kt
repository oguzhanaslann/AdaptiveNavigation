package com.oguzhanaslann.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(route: String): Screen(route) {

    abstract val label: String
    abstract val icon: ImageVector
    abstract val contentDescription: String?

    data object AccountScreen: MainScreen(routeBase("account")) {
        override val label = "Account"
        override val icon = Icons.Filled.AccountCircle
        override val contentDescription = "Account"
    }

    data object AccountDetailScreen: MainScreen(routeBase("accountDetail")) {
        override val label = "Account Detail"
        override val icon = Icons.Filled.AccountCircle
        override val contentDescription = "Account Detail"
    }

    data object SearchScreen: MainScreen(routeBase("search")) {
        override val label = "Search"
        override val icon = Icons.Filled.Search
        override val contentDescription = "Search"
    }

    data object SearchDetailScreen: MainScreen(routeBase("searchDetail")) {
        override val label = "Search Detail"
        override val icon = Icons.Filled.Search
        override val contentDescription = "Search Detail"
    }

    companion object {

        fun navigationScreens() = listOf(AccountScreen, SearchScreen)
        private fun routeBase(screenName: String) =
            "com.oguzhanaslann.adaptivenavigation.ui.screens.$screenName.screen"

        fun hostRoute() = "com.oguzhanaslann.adaptivenavigation.ui.screens.host.screen"

    }

}